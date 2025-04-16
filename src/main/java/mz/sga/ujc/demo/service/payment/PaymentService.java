package mz.sga.ujc.demo.service.payment;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.repository.candidatura.PagamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);
    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public PaymentService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;

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

    public Pagamento getPaymentByCandidate(Candidato Candidato){
        return pagamentoRepository.getPagamentoById_CandidatoId(Candidato.getCodigo());
    }
}
