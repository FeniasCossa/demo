package mz.sga.ujc.demo.service.payment;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.repository.candidatura.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PagamentoRepository pagamentoRepository;
    @Autowired
    public PaymentService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public void save(Pagamento pagamento){
        pagamentoRepository.save(pagamento);
    }

    public Pagamento getPaymentByCandidate(Candidato Candidato){
        return pagamentoRepository.getPagamentoById_Candidato(Candidato);
    }
}
