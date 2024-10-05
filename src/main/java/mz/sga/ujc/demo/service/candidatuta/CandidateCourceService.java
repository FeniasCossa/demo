package mz.sga.ujc.demo.service.candidatuta;

import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.model.restricoes.PagamentoPK;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateCourceService {

    private final SubjectCourseService subjectCourseService;
    private final CandidatoCursoRepository candidatoCursoRepository;
    private final PaymentService paymentService;
    @Autowired
    public CandidateCourceService(SubjectCourseService subjectCourseService, CandidatoCursoRepository candidatoCursoRepository, PaymentService paymentService) {
        this.subjectCourseService = subjectCourseService;
        this.candidatoCursoRepository = candidatoCursoRepository;
        this.paymentService = paymentService;
    }

    public void save(CandidatoCurso candidatoCurso){
        Pagamento pagamento = new Pagamento();
        pagamento.setId(new PagamentoPK(candidatoCurso.getId().getCandidato()));
        pagamento.setCurso( candidatoCurso.getId().getCurso());
        pagamento.setValor(subjectCourseService.getValor(candidatoCurso.getId().getCurso()));
        pagamento.setEstado("Nao Pago");
        CandidatoCurso cc= candidatoCursoRepository.getCandidatoCursoByIdCandidato(candidatoCurso.getId().getCandidato());
        if(cc!=null){
            candidatoCurso.setId(cc.getId());
            candidatoCursoRepository.save(candidatoCurso);
            paymentService.save(pagamento);
        }else{
            candidatoCursoRepository.save(candidatoCurso);
            paymentService.save(pagamento);
        }
    }
}
