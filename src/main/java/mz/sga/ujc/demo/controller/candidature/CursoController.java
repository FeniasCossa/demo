package mz.sga.ujc.demo.controller.candidature;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.DisciplinaCurso;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.repository.candidatura.CursoRepository;
import mz.sga.ujc.demo.repository.candidatura.DisciplinaCursoRepository;
import mz.sga.ujc.demo.service.candidatuta.DisciplinaCursoService;
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
import java.util.List;

@Controller
@RequestMapping("/course")
public class CursoController {
    private final ProvinciaService provinciaService;
    private final CursoRepository cursoRepository;
    private final CandidatoCursoRepository candidatoCursoRepository;
    private final DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private DisciplinaCursoService disciplinaCursoService;

    @Autowired
    public CursoController(ProvinciaService provinciaService, CursoRepository cursoRepository, CandidatoCursoRepository candidatoCursoRepository, DisciplinaCursoRepository disciplinaCursoRepository) {
        this.provinciaService = provinciaService;
        this.cursoRepository = cursoRepository;
        this.candidatoCursoRepository = candidatoCursoRepository;
        this.disciplinaCursoRepository = disciplinaCursoRepository;
    }

    @RequestMapping(path = "/regist", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getCours(@RequestParam("id") Integer id, ModelMap model){
        ModelAndView mv = new ModelAndView();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        mv.addObject("candidatoCurso", new CandidatoCurso());
        mv.addObject("provincias", provinciaService.listaProvincias());
        mv.addObject("codigo",id);
        mv.addObject("cursos",cursoRepository.findAll());
        mv.addObject("taxa",disciplinaCursoService.DistinctTotalByCurso());
        mv.addObject("disciplinasCursos",disciplinaCursos);
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
