package mz.sga.ujc.demo.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.sga.ujc.demo.model.auth.Perfil;
import mz.sga.ujc.demo.repository.auth.PerfilRepository;

@Service
public class PerfilService {
    
    @Autowired
    private PerfilRepository repository;

    public Perfil findPerfilById(Integer id){
        return repository.getReferenceById(id);
    }
}
