package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.model.restricoes.PagamentoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, PagamentoPK> {
    Pagamento getPagamentoById_Candidato(Candidato candidato);
}
