package mz.sga.ujc.demo.repository.candidatura;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer>{
 
    Documento getDocumentoByCandidato(Candidato candidato);
}
