package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.candidatura.DisciplinaCurso;
import mz.sga.ujc.demo.model.restricoes.DisciplinaCursoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaCursoRepository extends JpaRepository<DisciplinaCurso, DisciplinaCursoPk> {
}
