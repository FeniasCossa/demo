package mz.sga.ujc.demo.controller.auth;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.service.auth.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createAccount(ModelMap model) {
        model.addAttribute("conta", new Conta());
        return "account/create";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(@Valid Conta conta, BindingResult result) {
        if (result.hasErrors()) {
            return "account/create";
        }
        autoGenerateCodigo(conta);
        contaService.persist(conta);
        String msg = conta.getCodigo()+"_"+conta.getEmail();
        return "redirect:/email-send/"+msg;
    }

    @GetMapping("/alter")
    public ModelAndView alterar(@RequestParam("conta") Integer id, Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("account/edit");
        Conta conta1 = contaService.getContaByCodigo(id);
        model.addAttribute("conta", conta1);
        //mv.addObject("conta", new Conta());
        return mv;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid Conta conta, BindingResult result, RedirectAttributes attributes, ModelMap model) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            attributes.addFlashAttribute("fail", "Por favor preencha os dados coretamente");
            mv.setViewName("account/create");
            return mv;
        }
        mv.addObject("id", conta);
        mv.setViewName("redirect:/candidato/register");
        contaService.persist(conta);
        attributes.addFlashAttribute("telefonePrincipal", conta.getTelefone());
        return mv;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login(Conta conta) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("account/login");
        mv.addObject("conta", new Conta());
        return mv;
    }


    public void autoGenerateCodigo(Conta conta) {
        conta.setCodigo(getRandomInt(1000000, 1999999));
    }

    public int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
