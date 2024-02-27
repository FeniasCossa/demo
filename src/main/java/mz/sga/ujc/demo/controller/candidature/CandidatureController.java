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

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.Documento;
import mz.sga.ujc.demo.model.parametrization.Distrito;
import mz.sga.ujc.demo.model.parametrization.Escola;
import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.model.restricoes.DistritoPK;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.repository.candidatura.DocumentoRepository;
import mz.sga.ujc.demo.repository.parametrization.DistritoRepository;
import mz.sga.ujc.demo.repository.parametrization.EscolaRepostitory;
import mz.sga.ujc.demo.repository.parametrization.ProvinciaRepository;
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

    private final ContaService contaService;
    private final CandidatoService candidatoService;
    private final ProvinciaService provinciaService;
    private final DistritoRepository distritoRepository;
    private final CandidatoRepository candidatoRepository;
    private final DocumentoRepository documentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final ContaRepository contaRepository;
    private final EscolaRepostitory escolaRepostitory;

    @Autowired
    public CandidatureController(ContaService contaService, CandidatoService candidatoService, ProvinciaService provinciaService, DistritoRepository distritoRepository, CandidatoRepository candidatoRepository, DocumentoRepository documentoRepository, ProvinciaRepository provinciaRepository, ContaRepository contaRepository, EscolaRepostitory escolaRepostitory) {
        this.contaService = contaService;
        this.candidatoService = candidatoService;
        this.provinciaService = provinciaService;
        this.distritoRepository = distritoRepository;
        this.candidatoRepository = candidatoRepository;
        this.documentoRepository = documentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.contaRepository = contaRepository;
        this.escolaRepostitory = escolaRepostitory;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getForm1(@RequestParam("id") String id, Candidato candidato, ModelMap model) {
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
    public ModelAndView getData(@RequestParam("candidato") Integer id) {
        ModelAndView mv = new ModelAndView();
        Candidato candidato = candidatoRepository.getReferenceById(id);
        Documento documento = documentoRepository.getDocumentoByCandidato(candidato);
        Provincia provincia = provinciaRepository.getReferenceById(candidato.getDistrito().getId().getProvincia().getId());
        Distrito distrito = distritoRepository
                .getReferenceById(new DistritoPK(candidato.getDistrito().getId().getId(), candidato.getDistrito().getId().getProvincia()));
        Conta conta = contaRepository.getReferenceByCodigo(id);
        Escola escola = escolaRepostitory.getReferenceByCandidato(candidato);
        mv.addObject("candidato", candidato);
        mv.addObject("documento", documento);
        mv.addObject("provincia", provincia);
        mv.addObject("distrito", distrito);
        mv.addObject("conta", conta);
        mv.addObject("escola", escola);
        mv.setViewName("candidature/list/data");
        return mv;

    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login(BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors())
            mv.setViewName("candidature/login");
        else
            mv.setViewName("redirect: /home");
        return mv;
    }

}
