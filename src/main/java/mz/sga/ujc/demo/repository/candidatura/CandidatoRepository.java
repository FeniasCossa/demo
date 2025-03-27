package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.admin.Provenence;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

    Candidato getCandidatoByCodigo(Integer id);


    @Query("SELECT new mz.sga.ujc.demo.model.admin.Provenence(p.nome,(COUNT(c.codigo) * 100.0) /(SELECT COUNT(c2.codigo) FROM Candidato c2)) " +
            "FROM Candidato c " +
            "INNER JOIN c.distrito.id.provincia p " +
            "GROUP BY p.nome")
    List<Provenence> ListNameAndQuantity();
}