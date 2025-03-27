package mz.sga.ujc.demo.service.auth;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.repository.auth.PerfilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private final ContaRepository contaRepository;
    private final PerfilRepository perfilService;

    @Autowired
    public AccountService(ContaRepository contaRepository, PerfilRepository perfilService) {
        this.contaRepository = contaRepository;
        this.perfilService = perfilService;
        LOGGER.info("Initialing AccountService ... ");

    }

    public void save(Conta conta) {
        autoGenerateCodigo(conta);
        conta.setPerfil(perfilService.getReferenceById(1));
        conta.setSenha(criptText(conta.getSenha()));
        LOGGER.info("Save account");
        contaRepository.save(conta);
    }
    public void edit(Conta conta){
        Conta c = getAccountByCode(conta.getCodigo());
        c.setTelefone(conta.getTelefone());
        c.setNuit(conta.getNuit());
        c.setEmail(conta.getEmail());
        c.setSenha(criptText(conta.getSenha()));
        contaRepository.save(c);
    }

    public ModelAndView Login(@Valid Conta conta, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        LOGGER.info("found user");
        Conta account = getAccountByCode(conta.getCodigo());
        if (account == null) {
            LOGGER.info("User not found");
            mv.addObject("msg", "Codigo ou senha incorrecta");
            mv.setViewName("/account/login");
            return mv;
        }
        BCryptPasswordEncoder criPasswordEncoder = new BCryptPasswordEncoder();
        if(criPasswordEncoder.matches(conta.getSenha(),account.getSenha())){
            LOGGER.info("founded User");
            session.setAttribute("userlogado", account);
            if(account.getPerfil().getId()==1){
                mv.setViewName("redirect:/home/"+account.getCodigo());
            }if(account.getPerfil().getId()==2){
                mv.setViewName("redirect:/admin/home/"+account.getCodigo());
            }
            return mv;
        }
        LOGGER.info("User not found");
        mv.addObject("msg", "Codigo ou senha incorrecta");
        mv.setViewName("/account/login");
        return mv;

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
        LOGGER.info("Generate candidate code");
        conta.setCodigo(getRandomInt(1000000, 1999999));
    }
    public int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
    

