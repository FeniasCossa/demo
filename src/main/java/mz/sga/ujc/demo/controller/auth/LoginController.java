package mz.sga.ujc.demo.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/user")
public class LoginController {
    
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "candidature/login";
    }
}
