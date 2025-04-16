package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.exame.RealizacaoExame;
import mz.sga.ujc.demo.model.restricoes.RealizacaoExamePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealizacaoExameRepository extends JpaRepository<RealizacaoExame, RealizacaoExamePk> {
    List<RealizacaoExame> getRealizacaoExameByCandidato(Candidato candidato);
}
