package mz.sga.ujc.demo.controller.parametrization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.service.paramentrization.ProvinciaService;

@Controller
public class ProvinciaController {
    
    @Autowired
    private ProvinciaService service;

    @GetMapping(path = "/listSelect")
    public String createSelect(ModelMap model){
        model.addAttribute("provincias", service.listaProvincias());
        return "provin";
    }
}
