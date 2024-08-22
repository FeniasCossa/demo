package mz.sga.ujc.demo.controller;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.service.auth.AccountService;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    private final CandidateService candidateService;
    private final AccountService accountService;

    @Autowired
    public HomeController(CandidateService candidateService, AccountService accountService) {
        this.candidateService = candidateService;
        this.accountService = accountService;
    }

    @RequestMapping(path = "/home/{codigo}", method = RequestMethod.GET)
    public ModelAndView home(@PathVariable("codigo") Integer code) {
        return candidateService.getData(code,new ModelAndView("home/index"));
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView signin(@Valid Conta conta, HttpSession session) {
        return accountService.Login(conta,session);
    }
}
