package mz.sga.ujc.demo.service.payment;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.repository.candidatura.PagamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PaymentService {

    private static Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PagamentoRepository pagamentoRepository;
    private final CandidatoCursoRepository candidatoCursoRepository;
    @Autowired
    public PaymentService(PagamentoRepository pagamentoRepository, CandidatoCursoRepository candidatoCursoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.candidatoCursoRepository = candidatoCursoRepository;
    }

    public void save(Pagamento pagamento){
        try {
            if (!(getPaymentByCandidate(pagamento.getId().getCandidato()) == null)) {
                CandidatoCurso candidatoCurso = candidatoCursoRepository.getCandidatoCursoByIdCandidato(pagamento.getId().getCandidato());
                pagamento.setCurso(candidatoCurso.getId().getCurso());
                pagamento.setEstado("Pago");
                pagamentoRepository.save(pagamento);
                logger.info("Payment updated successfully with: {0} by: " + pagamento.getMetodoPagamento(), pagamento.getId().getCandidato().getCodigo() + " -->" + pagamento.getId().getCandidato().getNome());
            }
            pagamentoRepository.save(pagamento);
            logger.info("invoice created successfully by: " + pagamento.getId().getCandidato().getCodigo() + " -->" + pagamento.getId().getCandidato().getNome());
        }catch (Exception e){
            logger.warn("Failed to save payment detail");
            e.printStackTrace();
        }
    }

    public Pagamento getPaymentByCandidate(Candidato Candidato){
        return pagamentoRepository.getPagamentoById_Candidato(Candidato);
    }
}
