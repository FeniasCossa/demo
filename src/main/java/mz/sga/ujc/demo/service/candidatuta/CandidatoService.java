package mz.sga.ujc.demo.service.candidatuta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;

@Service
public class CandidatoService {
    
    @Autowired
    private CandidatoRepository repository;

    public void saveCandidato(Candidato Candidato){
        repository.save(Candidato);
    }
}
