package mz.sga.ujc.demo.service.auth;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.repository.auth.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final ContaRepository contaRepository;

    private final PerfilRepository perfilService;

    @Autowired
    public AccountService(ContaRepository contaRepository, PerfilRepository perfilService) {
        this.contaRepository = contaRepository;
        this.perfilService = perfilService;
    }

    public void save(Conta conta) {
        autoGenerateCodigo(conta);
        conta.setPerfil(perfilService.getReferenceById(1));
        conta.setSenha(criptText(conta.getSenha()));
        contaRepository.save(conta);
    }
    public void edit(Conta conta){
        Conta c = getAccountByNuit(conta.getNuit());
        if(conta.getSenha().equals(c.getSenha())){
            conta.setSenha(c.getSenha());
        }
        contaRepository.save(conta);
    }

    public String criptText(String text) {
        BCryptPasswordEncoder criPasswordEncoder = new BCryptPasswordEncoder();
        return criPasswordEncoder.encode(text);
    }

    public Conta getAccountByNuit(String id) {
        return contaRepository.getReferenceByNuit(id);
    }

    public Conta getAccountByCode(Integer codigo) {
        return contaRepository.getReferenceByCodigo(codigo);
    }
    public void autoGenerateCodigo(Conta conta) {
        conta.setCodigo(getRandomInt(1000000, 1999999));
    }
    public int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
    

