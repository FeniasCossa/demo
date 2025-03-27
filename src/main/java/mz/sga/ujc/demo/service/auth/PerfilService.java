package mz.sga.ujc.demo.service.auth;

import mz.sga.ujc.demo.model.auth.Perfil;
import mz.sga.ujc.demo.repository.auth.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PerfilService {


    private final PerfilRepository perfilRepository;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
        savePeril(new Perfil(1,"Candidato","Os que desejam candidar-se a UJC",new Date(),new Date()));
        savePeril(new Perfil(2,"amdn","Administrafor do Sistema",new Date(),new Date()));
    }


    public void savePeril(Perfil perfil){
        perfilRepository.save(perfil);
    }

    public Perfil getPerfilById(Integer id){
        return perfilRepository.getReferenceById(id);
    }

}
