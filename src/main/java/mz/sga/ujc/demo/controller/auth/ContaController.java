package mz.sga.ujc.demo.controller.auth;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.service.auth.AccountService;
import mz.sga.ujc.demo.service.auth.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static mz.sga.ujc.demo.utils.Utilities.*;

@Controller
@RequestMapping("/account")
public class ContaController {

    private final AccountService accountService;

    @Autowired
    private ContaRepository repository;
    @Autowired
    public ContaController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createAccount(ModelMap model) {
        model.addAttribute("conta", new Conta());
        return "account/create";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Conta conta, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("account/create");
        }else {
            if (repository.existsByEmail(conta.getEmail())) {
                return new ModelAndView("account/create","enailExist", EMAILEXISTS);
            } else if(repository.existsByNuit(conta.getNuit())){
                return new ModelAndView("account/create","enailExist", NUITEXISTS);
            }else{
                accountService.save(conta);
                SmsSender smsSender= new SmsSender();
                smsSender.send(Long.parseLong(conta.getTelefone()),"Teste pensa: "+conta.getCodigo());
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
}
