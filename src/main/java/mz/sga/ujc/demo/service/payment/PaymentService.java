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

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);
    private final PagamentoRepository pagamentoRepository;
    private final CandidatoCursoRepository candidatoCursoRepository;
    @Autowired
    public PaymentService(PagamentoRepository pagamentoRepository, CandidatoCursoRepository candidatoCursoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.candidatoCursoRepository = candidatoCursoRepository;
    }
    public int candidatosSemPagamentos(){
        return pagamentoRepository.candidatosSemPagamento();
    }
    public int candidatosComPagamentos(){
        return pagamentoRepository.candidatosComPagamento();
    }
    public double getAllPaymant(){
        return pagamentoRepository.getAllPayment() == null ? 0D : pagamentoRepository.getAllPayment();
    }

    public void save(Pagamento pagamento){
        try {
            if (!(getPaymentByCandidate(pagamento.getCandidato()) == null)) {
                CandidatoCurso candidatoCurso = candidatoCursoRepository.getCandidatoCursoByIdCandidatoId(pagamento.getCandidato().getCodigo());
                pagamento.setCurso(candidatoCurso.getCurso());
                pagamentoRepository.save(pagamento);
                LOGGER.info("Payment updated successfully with: {} by: " + pagamento.getMetodoPagamento(), pagamento.getCandidato().getCodigo() + " -->" + pagamento.getCandidato().getNome());
            }else {
                pagamentoRepository.save(pagamento);
                LOGGER.info("invoice created successfully by: " + pagamento.getCandidato().getCodigo() + " -->" + pagamento.getCandidato().getNome());
            }
            }catch (Exception e){
            LOGGER.warn("Failed to save payment detail");
            e.printStackTrace();
        }
    }

    public Pagamento getPaymentByCandidate(Candidato Candidato){
        return pagamentoRepository.getPagamentoById_CandidatoId(Candidato.getCodigo());
    }
}
