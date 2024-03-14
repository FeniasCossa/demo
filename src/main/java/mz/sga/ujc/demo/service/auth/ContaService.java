package mz.sga.ujc.demo.service.auth;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.repository.auth.PerfilRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private PerfilRepository perfilService;

    public void persist(Conta conta) {
        if (getContaByNuit(conta.getNuit()) == null) {
            conta.setPerfil(perfilService.getReferenceById(1));
            conta.setSenha(criptText(conta.getSenha()));
            contaRepository.save(conta);
        } else {
            contaRepository.save(conta);
        }
    }
    public String criptText(String text) {
        BCryptPasswordEncoder criPasswordEncoder = new BCryptPasswordEncoder();
        String cript = criPasswordEncoder.encode(text);
        return cript;
    }

    public Conta LoginUser(String codigo, String senha) throws ServiceException {
        Conta userLogin = contaRepository.buscarLogin(codigo, senha);
        return userLogin;
    }
    @Transactional(readOnly = true)
    public Conta getContaByNuit(String id) {
        return contaRepository.getReferenceByNuit(id);
    }

    public Conta getContaByCodigo(Object codigo) {
        return contaRepository.getReferenceByCodigo((int) codigo);
    }

    public Conta getContaById(String id) {
        return contaRepository.getReferenceById(id);
    }
}
    

