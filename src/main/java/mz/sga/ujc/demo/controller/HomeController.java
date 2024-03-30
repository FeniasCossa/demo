package mz.sga.ujc.demo.controller;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    private final ContaRepository repository;

    @Autowired
    public HomeController(ContaRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home() {
        return "home/index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView signin(@Valid Conta conta, BindingResult result, HttpSession session, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        Conta account = repository.getReferenceByCodigo(conta.getCodigo());
        if (account == null) {
            mv.addObject("msg", "Codigo ou senha incorrecta");
            mv.setViewName("/account/login");
            return mv;
        }
        BCryptPasswordEncoder criPasswordEncoder = new BCryptPasswordEncoder();
        if(criPasswordEncoder.matches(conta.getSenha(),account.getSenha())){
            session.setAttribute("userlogado", account);
            mv.setViewName("redirect:/home");
            return mv;
        }
        mv.addObject("msg", "Codigo ou senha incorrecta");
        mv.setViewName("/account/login");
        return mv;
    }
}
