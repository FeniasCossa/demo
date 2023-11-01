package mz.sga.ujc.demo.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RestController("/candidato")
public class LoginController {
    
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        return new ModelAndView("candidature/login");
    }
}
