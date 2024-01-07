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

import mz.sga.ujc.demo.model.candidatura.Documento;
import mz.sga.ujc.demo.model.parametrization.Escola;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.repository.candidatura.DocumentoRepository;
import mz.sga.ujc.demo.repository.parametrization.EscolaRepostitory;
import mz.sga.ujc.demo.service.paramentrization.ProvinciaService;

@Controller
@RequestMapping("/document")
public class DocumentoController {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private EscolaRepostitory escolaRepostitory;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView formDocumente(@RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("candidature/register/doc");
        mv.addObject("candidato", candidatoRepository.getReferenceById(id));
        mv.addObject("provincias", provinciaService.listaProvincias());
        return mv;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Escola escola, @Valid Documento documento, BindingResult result, ModelMap model) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv.addObject("id", documento.getCandidato());
            mv.setViewName("redirect:/document");
            return mv;
        }
        mv.addObject("id", documento.getCandidato());
        mv.addObject("es", escola.getCandidato());
        mv.setViewName("redirect:/candidato/getData");
        documentoRepository.save(documento);
        escolaRepostitory.save(escola);
        return mv;
    }

}
