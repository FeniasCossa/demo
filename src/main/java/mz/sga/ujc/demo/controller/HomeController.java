package mz.sga.ujc.demo.controller;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.service.auth.AccountService;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    private final CandidateService candidateService;
    private final AccountService accountService;

    @Autowired
    public HomeController(CandidateService candidateService, AccountService accountService) {
        this.candidateService = candidateService;
        this.accountService = accountService;
        LOGGER.info("Inicializing HomeController ...");
    }

    @RequestMapping(path = "/home/{codigo}", method = RequestMethod.GET)
    public ModelAndView home(@PathVariable("codigo") Integer code) {
        LOGGER.info("Getting info for code - {} to getting Home page ... ",code);
        return candidateService.getData(code,new ModelAndView("home/index"));
    }


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView signin(@Valid Conta conta, HttpSession session) {
        LOGGER.info("Authenticating user ... ");
        return accountService.Login(conta,session);
    }

    @RequestMapping(path = "/redirect_user_payment/{codigo}", method = RequestMethod.GET)
    public ModelAndView AutoSignin(@PathVariable("codigo") Integer codigo) {
        return new ModelAndView("redirect:/payment?redindn-00409-0000-Join="+ codigo);
    }
}
