package mz.sga.ujc.demo.service.candidatuta;

import mz.sga.ujc.demo.model.admin.Provenance;
import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.Documento;
import mz.sga.ujc.demo.model.candidatura.Factura;
import mz.sga.ujc.demo.model.parametrization.Distrito;
import mz.sga.ujc.demo.model.parametrization.Escola;
import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.model.restricoes.DistritoPK;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.repository.candidatura.DocumentoRepository;
import mz.sga.ujc.demo.repository.parametrization.DistritoRepository;
import mz.sga.ujc.demo.repository.parametrization.EscolaRepostitory;
import mz.sga.ujc.demo.repository.parametrization.ProvinciaRepository;
import mz.sga.ujc.demo.service.auth.AccountService;
import mz.sga.ujc.demo.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidatoRepository repository;
    private final AccountService accountService;
    private final CandidatoRepository candidatoRepository;
    private final DocumentoRepository documentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final ContaRepository contaRepository;
    private final EscolaRepostitory escolaRepostitory;
    private final DistritoRepository distritoRepository;
    private final CandidatoCursoRepository candidatoCursoRepository;
    private final PaymentService paymentService;
    private final SubjectCourseService subjectCourseService;

    @Autowired
    public CandidateService(CandidatoRepository repository, AccountService accountService, CandidatoRepository candidatoRepository, DocumentoRepository documentoRepository, ProvinciaRepository provinciaRepository, ContaRepository contaRepository, EscolaRepostitory escolaRepostitory, DistritoRepository distritoRepository, CandidatoCursoRepository candidatoCursoRepository, PaymentService paymentService, SubjectCourseService subjectCourseService) {
        this.repository = repository;
        this.accountService = accountService;
        this.candidatoRepository = candidatoRepository;
        this.documentoRepository = documentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.contaRepository = contaRepository;
        this.escolaRepostitory = escolaRepostitory;
        this.distritoRepository = distritoRepository;
        this.candidatoCursoRepository = candidatoCursoRepository;
        this.paymentService = paymentService;
        this.subjectCourseService = subjectCourseService;
    }

    public void save(Candidato candidato, Integer code) {
        candidato.setCodigo(code);
        candidato.setConta(accountService.getAccountByCode(code));
        repository.save(candidato);
    }

    public List<Candidato> listCandidato() {
        return candidatoRepository.findAll();
    }

    public List<Provenance> listProvinceAndQuantity() {
        return candidatoRepository.ListNameAndQuantity();
    }

    public long CountAll() {
        return candidatoRepository.count();
    }

    public Candidato getCandidateByCode(Integer codigo) {
        return repository.getCandidatoByCodigo(codigo);
    }

    public List<Provenance> CountAllByDay() {
        return mapTuplesToProvenance(repository.countAllByCreatedAt());
    }

    public List<Provenance> mapTuplesToProvenance(List<Tuple> tuples) {
        return tuples.stream().map(tuple -> {
            Provenance provenance = new Provenance();
            provenance.setDate(tuple.get("date", Date.class));
            provenance.setTotalPorDia(tuple.get("totalPorDia", Double.class));
            return provenance;
        }).collect(Collectors.toList());
    }


    public ModelAndView getData(Integer id, ModelAndView mv) {
        CandidatoCurso candidatoCurso = candidatoCursoRepository.getCandidatoCursoByIdCandidatoId(id);
        if (candidatoCurso == null) {
            return new ModelAndView("redirect:/course/regist?id=" + id);
        }
        Factura factura = subjectCourseService.getFactura(candidatoCurso);
        mv.addObject("candidatoCurso", candidatoCurso);
        mv.addObject("fatura", factura);
        mv.addObject("userlogado", accountService.getAccountByCode(id));
        return previewDate(id, mv);
    }

    public ModelAndView previewDate(Integer id, ModelAndView mv) {
        Provincia provincia = provinciaRepository.getReferenceById(
                candidatoRepository.getReferenceById(id).getDistrito().getId().getProvincia().getId());
        Distrito distrito = distritoRepository.getReferenceById(
                new DistritoPK(
                        candidatoRepository.getReferenceById(id).getDistrito().getId().getId(),
                        candidatoRepository.getReferenceById(id).getDistrito().getId().getProvincia()
                ));
        Conta conta = contaRepository.getReferenceByCodigo(candidatoRepository.getReferenceById(id).getCodigo());
        Documento documento = documentoRepository.getDocumentoByCandidato(candidatoRepository.getReferenceById(id));
        Candidato candidato = candidatoRepository.getCandidatoByCodigo(id);
        Escola escola = escolaRepostitory.getReferenceByCandidato(candidatoRepository.getReferenceById(id));
        if (conta == null) {
            return new ModelAndView("redirect:/account/create");
        }
        if (candidato == null) {
            return new ModelAndView("redirect:/candidato/register?id=" + id);
        }
        if (documento == null || escola == null) {
            return new ModelAndView("redirect:/document?id=" + id);
        }
        mv.addObject("candidato", candidato);
        mv.addObject("documento", documento);
        mv.addObject("provincia", provincia);
        mv.addObject("distrito", distrito);
        mv.addObject("conta", conta);
        mv.addObject("escola", escola);
        return mv;
    }
}
