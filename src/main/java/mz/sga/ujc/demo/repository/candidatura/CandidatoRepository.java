package mz.sga.ujc.demo.repository.candidatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.sga.ujc.demo.model.candidatura.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{

}