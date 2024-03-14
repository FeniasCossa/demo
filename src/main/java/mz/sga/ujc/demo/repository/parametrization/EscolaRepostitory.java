package mz.sga.ujc.demo.repository.parametrization;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.parametrization.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscolaRepostitory extends JpaRepository<Escola, Integer> {

    Escola getReferenceByCandidato(Candidato candidato);
}
