package mz.sga.ujc.demo.controller.candidature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class ContaController {
    
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createAccount(){
        return "account/create";
    }
    
}
