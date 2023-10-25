package mz.sga.ujc.demo.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
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
        conta.setPerfil(service.findPerfilById(1));
        conta.setCodigo(conta.generateCodigo());
        System.out.println("sera que voce chega aqui?");
        if(burcarContaPorNuit(conta.getNuit())==null){
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
    

