package mz.sga.ujc.demo.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mz.sga.ujc.demo.model.auth.Perfil;
import mz.sga.ujc.demo.service.auth.PerfilService;

@Controller
@RestController("/perfil")
public class PerfilController {
    
    @Autowired
    private PerfilService service;

    @RequestMapping(path = "save", method = RequestMethod.POST)
    public String save(@Valid Perfil perfil){

        return "redirect: /perfil/list";
    }
    
}
