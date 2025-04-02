package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.admin.Provenance;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

    Candidato getCandidatoByCodigo(Integer id);


    @Query("SELECT new mz.sga.ujc.demo.model.admin.Provenance(p.nome,(COUNT(c.codigo) * 100.0) /(SELECT COUNT(c2.codigo) FROM Candidato c2)) " +
            "FROM Candidato c " +
            "INNER JOIN c.distrito.id.provincia p " +
            "GROUP BY p.nome")
    List<Provenance> ListNameAndQuantity();

    @Query("select Date(c.createdAt) as date, (count(c.codigo)*100.0 / (select count(cc.codigo) from Candidato cc)) as totalPorDia from Candidato c group by Date(c.createdAt)")
    List<Tuple> countAllByCreatedAt();

}