package mz.sga.ujc.demo.controller.candidature;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.Documento;
import mz.sga.ujc.demo.model.parametrization.Escola;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.service.candidatuta.SchoolAndDocumentService;
import mz.sga.ujc.demo.service.paramentrization.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/document")
public class DocumentoController {

    private final CandidatoRepository candidatoRepository;
    private final ProvinceService provinceService;
    private final SchoolAndDocumentService schoolAndDocumentService;

    @Autowired
    public DocumentoController(CandidatoRepository candidatoRepository, ProvinceService provinceService, SchoolAndDocumentService schoolAndDocumentService) {
        this.candidatoRepository = candidatoRepository;
        this.provinceService = provinceService;
        this.schoolAndDocumentService = schoolAndDocumentService;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView formDocument(@RequestParam("id") Integer id) {
        Candidato candidato=candidatoRepository.getReferenceById(id);
        if(candidato.getCodigo()==null){
            return new ModelAndView("redirect:/candidato/register?id="+id);
        }
        ModelAndView mv = new ModelAndView("candidature/register/doc");
        mv.addObject("candidato", candidatoRepository.getReferenceById(id));
        mv.addObject("provincias", provinceService.provinceList());
        return mv;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Escola escola, @Valid Documento documento, BindingResult result) {
        if (result.hasErrors()) {
           return new ModelAndView("redirect:/document?id="+documento.getCandidato().getCodigo());
        }
        schoolAndDocumentService.save(documento,escola);
        return new ModelAndView("redirect:/candidato/getData?candidato="+documento.getCandidato().getCodigo());
    }
}
