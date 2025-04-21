package mz.sga.ujc.demo.controller.auth;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.service.Info.SmsSender;
import mz.sga.ujc.demo.service.auth.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static mz.sga.ujc.demo.utils.Utilities.EMAILEXISTS;
import static mz.sga.ujc.demo.utils.Utilities.NUITEXISTS;

@Controller
@RequestMapping("/account")
public class ContaController {

    private static final Logger logger= LoggerFactory.getLogger(ContaController.class);

    private final AccountService accountService;
    private final ContaRepository repository;
    private final CandidatoCursoRepository candidatoCursoRepo;
    private final CandidatoRepository candidatoRepository;
    @Autowired
    public ContaController(AccountService accountService, ContaRepository repository, CandidatoCursoRepository candidatoCursoRepo, CandidatoRepository candidatoRepository) {
        this.accountService = accountService;
        this.repository=repository;
        this.candidatoCursoRepo = candidatoCursoRepo;
        this.candidatoRepository = candidatoRepository;
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createAccount(ModelMap model) {
        logger.info("Renderizando a view Criar conta");
        model.addAttribute("conta", new Conta());
        return "account/create";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Conta conta, BindingResult result) {
        ModelAndView mv= new ModelAndView();
        if (result.hasErrors()) {
            logger.warn("Erros encontrados nos atributos, Devolvendo a pagina para a coreção --- ");
            mv.setViewName("account/create");
            mv.addObject("conta",conta);
            return mv;
        }else {
            List<String> error = new ArrayList<>();
            if (repository.existsByEmail(conta.getEmail())) {
                logger.warn("Email encotrado, não pode criar conta");
                error.add(EMAILEXISTS);
            } else if (repository.existsByNuit(conta.getNuit())) {
                logger.warn("Nuit encotrado, não pode criar conta");
                error.add(NUITEXISTS);
            }
            if (!error.isEmpty()){
                return new ModelAndView("account/create", "emailExist", error);
            }else{
                accountService.save(conta);
                SmsSender smsSender = new SmsSender(Long.parseLong(conta.getTelefone()),"Teste pensa: "+conta.getCodigo());
                Thread thread= new Thread(smsSender);
                thread.start();
                String msg = conta.getCodigo() + "_" + conta.getEmail();
                return new ModelAndView("redirect:/email-send/" + msg);
            }
        }
    }

    @GetMapping("/alter/{codigo}")
    public ModelAndView alterar(@PathVariable("codigo") Integer codigo) {
        ModelAndView mv= new ModelAndView("account/edit");
        Conta conta = accountService.getAccountByCode(codigo);
        mv.addObject("cont",new Conta());
        return new ModelAndView("account/edit","conta", conta);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid Conta conta, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("account/create");
        }
        accountService.edit(conta);
        return new ModelAndView("redirect:/candidato/register?id="+conta.getCodigo());
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("account/login","conta", new Conta());
    }

    @RequestMapping(path = "/recover", method = RequestMethod.GET)
    public ModelAndView recover(){
        return new ModelAndView("/account/recover");
    }

    @RequestMapping(path = "profile/{codigo}", method = RequestMethod.GET)
    public String profile(@PathVariable("codigo") Integer codigo, Model model){
        Candidato  candidato= candidatoRepository.getReferenceById(codigo);
        model.addAttribute("conta", candidato);
        model.addAttribute("candidato", candidato);
        model.addAttribute("userlogado", repository.getReferenceByCodigo(codigo));
        model.addAttribute("user", candidatoCursoRepo.getCandidatoCursoByIdCandidatoId(codigo));

        return "/account/profile";
    }

}
