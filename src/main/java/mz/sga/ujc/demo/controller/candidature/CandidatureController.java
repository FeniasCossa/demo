/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sga.ujc.demo.controller.candidature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.parametrization.Distrito;
import mz.sga.ujc.demo.repository.parametrization.DistritoRepository;
import mz.sga.ujc.demo.service.candidatuta.CandidatoService;
import mz.sga.ujc.demo.service.paramentrization.ProvinciaService;

/**
 *
 * @author Fenias Cossa
 */
@Controller
@RequestMapping("/candidato")
public class CandidatureController {

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private DistritoRepository distritoRepository;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getForm1(Candidato candidato, ModelMap model) {
        model.addAttribute("provincias", provinciaService.listaProvincias());
        model.addAttribute("distritos", distritoRepository.findAll());
        return "candidature/register/register";
        
    }
    
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(Candidato candidato){
        candidatoService.saveCandidato(candidato);
        return "redirect:/candidato/documento";
    }

    @RequestMapping(path = "/documento", method=RequestMethod.POST)
    public String formDocumente(){
        return "Opha";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "candidature/login";
    }

}
