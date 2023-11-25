package mz.sga.ujc.demo.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.service.auth.ContaService;

@Controller
@RequestMapping("/account")
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private ContaRepository repository;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createAccount() {
        return "account/create";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView saveAccount(@Valid Conta conta, BindingResult result, RedirectAttributes attributes, ModelMap model) {
        ModelAndView mv=new ModelAndView();
        if (result.hasErrors()) {
            attributes.addFlashAttribute("fail", "Por favor preencha os dados coretamente");
            mv.setViewName("account/create");
            return mv;
        }     
        mv.addObject("id", conta);
        mv.setViewName("redirect:/candidato/register");   
        autoGenerateCodigo(conta);
        contaService.persist(conta);
        attributes.addFlashAttribute("telefonePrincipal", conta.getTelefone());
        attributes.addFlashAttribute("success","O seu codigo é "+ conta.getCodigo()+ "\ngrave o seu condigo para usares quando for a fazer login");
        return mv;
    }

    public void autoGenerateCodigo(Conta conta) {
        int contador = 100000;
        if (repository.getCodigo()!=0) {
            conta.setCodigo(repository.getCodigo() + 1);
        } else {
            conta.setCodigo(contador++);
        }
    }

}
