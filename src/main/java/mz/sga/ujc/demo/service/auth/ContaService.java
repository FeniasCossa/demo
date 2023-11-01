package mz.sga.ujc.demo.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.repository.auth.ContaRepository;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepository repository;

    @Autowired
    private PerfilService service;  

    public void persist(Conta conta){
        if(burcarContaPorNuit(conta.getNuit())==null){
            conta.setCodigo(Conta.generateCodigo());
            conta.setPerfil(service.findPerfilById(1));
            BCryptPasswordEncoder criPasswordEncoder=new BCryptPasswordEncoder();
            String senhaCriptografada = criPasswordEncoder.encode(conta.getSenha());
            conta.setSenha(senhaCriptografada);
            repository.save(conta);
        }else{
            repository.save(conta);
        }
    }

    @Transactional(readOnly = true)
    public Conta burcarContaPorNuit(Integer id){
        return  repository.getReferenceByNuit(id);
    }
}
    

