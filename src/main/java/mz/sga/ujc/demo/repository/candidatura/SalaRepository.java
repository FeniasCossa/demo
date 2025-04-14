package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.exame.Instituicao;
import mz.sga.ujc.demo.model.exame.Sala;
import mz.sga.ujc.demo.model.restricoes.SalaPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, SalaPk> {
    List<Sala> findByInstituicao(Instituicao instituicao);

}
