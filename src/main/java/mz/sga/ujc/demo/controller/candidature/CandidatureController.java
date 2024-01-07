/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sga.ujc.demo.controller.candidature;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.repository.candidatura.DocumentoRepository;
import mz.sga.ujc.demo.repository.parametrization.DistritoRepository;
import mz.sga.ujc.demo.service.auth.ContaService;
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
    private ContaService contaService;
    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private DistritoRepository distritoRepository;
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private DocumentoRepository documentoRepository;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getForm1(@RequestParam("id") Integer id, Candidato candidato, ModelMap model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("candidature/register/register");
        model.addAttribute("provincias", provinciaService.listaProvincias());
        model.addAttribute("distritos", distritoRepository.findAll());
        model.addAttribute("conta", contaService.getContaById(id));
        return mv;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Candidato candidato, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv.addObject("id", contaService.getContaByCodigo(candidato.getCodigo()));
            mv.setViewName("redirect:/candidato/register");
            return mv;
        }
        mv.addObject("id", candidato);
        candidatoService.saveCandidato(candidato);
        mv.setViewName("redirect:/document");
        return mv;

    }

    @RequestMapping(path = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getData(@RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("candidato", candidatoRepository.getReferenceById(id));
        // mv.addObject("conta", contaService.getContaById(id));
        mv.setViewName("candidature/list/data");
        return mv;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "candidature/login";
    }

}
