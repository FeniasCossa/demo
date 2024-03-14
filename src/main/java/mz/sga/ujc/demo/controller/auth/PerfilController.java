package mz.sga.ujc.demo.controller.auth;

import mz.sga.ujc.demo.model.auth.Perfil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@RestController("/perfil")
public class PerfilController {


    @RequestMapping(path = "save", method = RequestMethod.POST)
    public String save(@Valid Perfil perfil) {

        return "redirect: /perfil/list";
    }

}
