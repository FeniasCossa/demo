package mz.sga.ujc.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.service.auth.ContaService;

@Controller
public class HomeController {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private ContaService service;

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home() {
        return "home/index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView signin(@Valid Conta conta, BindingResult result, HttpSession session,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            attributes.addAttribute("fail", "Usuario osfs orrecta");
            mv.setViewName("/error");
            return mv;
        }
        Conta account = repository.getReferenceByCodigo(conta.getCodigo());
        System.out.println("Usuario encontrado"+ account.toString());
        if (account.getSenha().equals(service.criptText(conta.getSenha()))) {
            session.setAttribute("userlogado", account);
            mv.setViewName("redirect:/home");
            return mv;
        } else {
            attributes.addAttribute("fail", "Usuario ou senha incorrecta");
            mv.setViewName("/error");
            return mv;
        }
    }
    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public ModelAndView signin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/account/login");
        return mv;
    }
}
