package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.candidatura.Disciplina;
import mz.sga.ujc.demo.model.exame.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExameRepository  extends JpaRepository<Exame, Integer> {

    Exame getExameByDisciplina(Disciplina disciplina);
}
