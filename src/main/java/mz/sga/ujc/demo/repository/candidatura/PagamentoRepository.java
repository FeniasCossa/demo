package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.candidatura.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    Pagamento getPagamentoById_CandidatoId(Integer candidato);

    @Query("select count(c.codigo) from Pagamento p left join Candidato c on p.candidato.codigo=c.codigo where p.estado = 'Pago'")
    int candidatosComPagamento();


    @Query("select count(c.codigo) from Candidato c left join  Pagamento p  on p.candidato.codigo=c.codigo where p.estado != 'Pago' or p.id is null")
    int candidatosSemPagamento();

    @Query(value = "select sum(valor) from Pagamento where estado = 'Pago'")
    Double getAllPayment();
}
