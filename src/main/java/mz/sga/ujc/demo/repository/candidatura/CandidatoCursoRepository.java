package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.restricoes.CandidatoCursoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoCursoRepository extends JpaRepository<CandidatoCurso, CandidatoCursoPk> {

    CandidatoCurso getCandidatoCursoByIdCandidato(Candidato candidato);
}
