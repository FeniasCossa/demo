package mz.sga.ujc.demo.controller.candidature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mz.sga.ujc.demo.service.paramentrization.ProvinciaService;

@Controller
@RequestMapping("/course")
public class CursoController {
    @Autowired
    private ProvinciaService provinciaService;

    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getCours(ModelMap model){
        model.addAttribute("provincias", provinciaService.listaProvincias());
        return "candidature/course/create";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String saveCourse(){
        return "redirect:/course/fatura";
    }

    @RequestMapping(path = "/fatura", method = RequestMethod.GET)
    public String getFatura(){
        return "candidature/list/invoice";
    }
}
