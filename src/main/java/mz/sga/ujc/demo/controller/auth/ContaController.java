package mz.sga.ujc.demo.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.service.auth.ContaService;

@Controller
@RequestMapping("/account")
public class ContaController {
    
    @Autowired
    private ContaService contaService;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createAccount(){
        return "account/create";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String saveAccount(@Valid Conta conta, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            System.out.println("Qual é o erro agora?");
            System.out.println(conta);
            return "account/create";
        }
        contaService.persist(conta);
        attributes.addFlashAttribute("success", "grave o seu condigo para usares para fazer login");
        return "redirect:/candidatura/register";
    }
    
}
