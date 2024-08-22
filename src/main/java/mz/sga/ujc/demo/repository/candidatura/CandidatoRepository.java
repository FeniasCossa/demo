package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

    Candidato getCandidatoByCodigo(Integer id);
}