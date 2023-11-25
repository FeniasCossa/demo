package mz.sga.ujc.demo.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.model.auth.Perfil;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.repository.auth.PerfilRepository;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepository repository;

    @Autowired
    private PerfilRepository perfilService;  



    public void persist(Conta conta){
        if(getContaByNuit(conta.getNuit())==null){
            conta.setPerfil(perfilService.getReferenceById(1));
            conta.setSenha(criptText(conta.getSenha()));
            repository.save(conta);
        }else{
            repository.save(conta);
        }
    }

    public String criptText(String text){
            BCryptPasswordEncoder criPasswordEncoder=new BCryptPasswordEncoder();
            String cript = criPasswordEncoder.encode(text);
            return cript;
    }

    @Transactional(readOnly = true)
    public Conta getContaByNuit(Integer id){
        return  repository.getReferenceByNuit(id);
    }
}
    

