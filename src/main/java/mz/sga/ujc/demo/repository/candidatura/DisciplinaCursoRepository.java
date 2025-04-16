package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.candidatura.Curso;
import mz.sga.ujc.demo.model.candidatura.DisciplinaCurso;
import mz.sga.ujc.demo.model.restricoes.DisciplinaCursoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaCursoRepository extends JpaRepository<DisciplinaCurso, DisciplinaCursoPk> {

    List<DisciplinaCurso> getDisciplinaCursoByIdCurso(Curso curso);
}
