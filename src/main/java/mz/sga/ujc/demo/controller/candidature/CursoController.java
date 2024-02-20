package mz.sga.ujc.demo.controller.candidature;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.repository.candidatura.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mz.sga.ujc.demo.service.paramentrization.ProvinciaService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/course")
public class CursoController {
    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private CandidatoCursoRepository candidatoCursoRepository;
    
    @RequestMapping(path = "/regist", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getCours(@RequestParam("id") Integer id, ModelMap model){
        ModelAndView mv = new ModelAndView();
        mv.addObject("candidatoCurso", new CandidatoCurso());
        mv.addObject("provincias", provinciaService.listaProvincias());
        mv.addObject("codigo",id);
        mv.addObject("cursos",cursoRepository.findAll());
        mv.setViewName("candidature/course/create");
        return mv;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String saveCourse(@Valid CandidatoCurso candidatoCurso, BindingResult result,ModelMap model){
        if(result.hasErrors()) {
            return "/course/regist";
        }
        candidatoCursoRepository.save(candidatoCurso);
        model.addAttribute("candidatoCurso",candidatoCurso);
        return "redirect:/course/fatura";
    }

    @RequestMapping(path = "/fatura", method = RequestMethod.GET)
    public String getFatura(){
        return "candidature/list/invoice";
    }
}
