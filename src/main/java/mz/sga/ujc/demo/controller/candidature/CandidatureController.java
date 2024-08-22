/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sga.ujc.demo.controller.candidature;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.repository.auth.ContaRepository;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.repository.candidatura.DocumentoRepository;
import mz.sga.ujc.demo.repository.parametrization.DistritoRepository;
import mz.sga.ujc.demo.repository.parametrization.EscolaRepostitory;
import mz.sga.ujc.demo.repository.parametrization.ProvinciaRepository;
import mz.sga.ujc.demo.service.auth.AccountService;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import mz.sga.ujc.demo.service.paramentrization.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author Fenias Cossa
 */
@Controller
@RequestMapping("/candidato")
public class CandidatureController {

    private final AccountService accountService;
    private final CandidateService candidateService;
    private final ProvinceService provinceService;
    private final DistritoRepository distritoRepository;

    @Autowired
    public CandidatureController(AccountService accountService, CandidateService candidateService, ProvinceService provinceService, DistritoRepository distritoRepository, CandidatoRepository candidatoRepository, DocumentoRepository documentoRepository, ProvinciaRepository provinciaRepository, ContaRepository contaRepository, EscolaRepostitory escolaRepostitory) {
        this.accountService = accountService;
        this.candidateService = candidateService;
        this.provinceService = provinceService;
        this.distritoRepository = distritoRepository;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public ModelAndView getForm1(@RequestParam("id") Integer id, ModelMap model) {
        Conta conta = accountService.getAccountByCode(id);
        if(conta==null){
            return  new ModelAndView("redirect:/account/create");
        }
        ModelAndView mv = new ModelAndView("candidature/register/register","candidato", new Candidato());
        model.addAttribute("provincias", provinceService.provinceList());
        model.addAttribute("distritos", distritoRepository.findAll());
        model.addAttribute("conta", accountService.getAccountByCode(id));
        return mv;
    }

    @RequestMapping(path = "/save/{code}", method = RequestMethod.POST)
    public ModelAndView save(@PathVariable("code") Integer code, @Valid Candidato candidato, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/candidato/register?id=" + accountService.getAccountByCode(candidato.getCodigo()).getCodigo());
        }
        candidateService.save(candidato,code);
        return new ModelAndView("redirect:/document?id="+candidato.getCodigo());
   }

    @RequestMapping(path = "/getData", method = RequestMethod.GET)
    public ModelAndView getData(@RequestParam("candidato") Integer id) {
        return  candidateService.previewDate(id,new ModelAndView("candidature/list/data"));
    }

}
