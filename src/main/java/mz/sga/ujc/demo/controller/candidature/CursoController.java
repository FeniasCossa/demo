package mz.sga.ujc.demo.controller.candidature;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.Curso;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.repository.candidatura.CursoRepository;
import mz.sga.ujc.demo.repository.candidatura.DisciplinaCursoRepository;
import mz.sga.ujc.demo.repository.parametrization.ProvinciaRepository;
import mz.sga.ujc.demo.service.candidatuta.CandidateCourceService;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import mz.sga.ujc.demo.service.candidatuta.SubjectCourseService;
import mz.sga.ujc.demo.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/course")
public class CursoController {
    private final ProvinciaRepository provinciaRepo;
    private final CursoRepository cursoRepository;
    private final CandidateCourceService candidateCourceService;
    private final CandidatoCursoRepository candidatoCursoRepo;
    private final DisciplinaCursoRepository disciplinaCursoRepository;
    private final SubjectCourseService subjectCourseService;
    private final PaymentService paymentService;
    private final CandidateService candidateService;
    private final CandidatoRepository candidatoRepo;

    @Autowired
    public CursoController(ProvinciaRepository provinceService, CursoRepository cursoRepository, DisciplinaCursoRepository disciplinaCursoRepository, SubjectCourseService subjectCourseService, CandidateCourceService candidateCourceService, CandidatoCursoRepository candidatoCursoRepo, PaymentService paymentService, CandidateService candidateService, CandidatoRepository candidatoRepo) {
        this.provinciaRepo = provinceService;
        this.cursoRepository = cursoRepository;
        this.candidateCourceService = candidateCourceService;
        this.disciplinaCursoRepository = disciplinaCursoRepository;
        this.subjectCourseService = subjectCourseService;
        this.candidatoCursoRepo = candidatoCursoRepo;
        this.candidateService = candidateService;
        this.paymentService = paymentService;
        this.candidatoRepo = candidatoRepo;
    }

    @RequestMapping(path = "/regist", method = RequestMethod.GET)
    public ModelAndView getCourses(@RequestParam("id") Integer id) {
        Candidato candidato=candidateService.getCandidateByCode(id);
        if(candidato==null){
            return new ModelAndView("redirect:/candidato/register?id="+id);
        }
        ModelAndView mv = new ModelAndView("candidature/course/create");
        mv.addObject("candidatoCurso", new CandidatoCurso());
        mv.addObject("provincias", provinciaRepo.findAll());
        mv.addObject("cursos", cursoRepository.findAll());
        mv.addObject("codigo", id);
        mv.addObject("taxa", subjectCourseService.DistinctTotalByCource());
        mv.addObject("disciplinasCursos", disciplinaCursoRepository.findAll());
            return mv;
    }

 //   @RequestMapping(path = "/save", method = RequestMethod.POST)
//    public ModelAndView saveCourse(@Valid CandidatoCurso candidatoCurso, BindingResult result) {
//        if (result.hasErrors()) {
//            return new ModelAndView("redirect:/course/regist?id="+candidatoCurso.getId().getCandidato().getCodigo());
//        }
//        candidatoCurso.setId(new CandidatoCursoPk(candidatoCurso.getCandidato(), candidatoCurso.getCurso(),candidatoCurso.getProvincia()));
//        candidateCourceService.save(candidatoCurso);
//        return new ModelAndView("redirect:/course/fatura?candidato="+candidatoCurso.getId().getCandidato().getCodigo());
//    }


    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView saveCourse(@Valid CandidatoCurso candidatoCurso, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("redirect:/course/regist?id="+ candidatoCurso.getId().getCandidatoId());
        }
        Candidato candidato = candidatoRepo.findById(candidatoCurso.getId().getCandidatoId()).orElseThrow();
        Curso curso = cursoRepository.findById(candidatoCurso.getId().getCursoId()).orElseThrow();
        Provincia provincia = provinciaRepo.findById(candidatoCurso.getId().getProvinciaId()).orElseThrow();

        candidatoCurso.setCandidato(candidato);
        candidatoCurso.setCurso(curso);
        candidatoCurso.setProvincia(provincia);

        candidateCourceService.save(candidatoCurso);

        return new ModelAndView("redirect:/course/fatura?candidato=" + candidato.getCodigo());
    }

    @RequestMapping(path = "/fatura", method = RequestMethod.GET)
    public ModelAndView getFatura(@RequestParam("candidato") Integer id) {
        CandidatoCurso candidatoCurso = candidatoCursoRepo.getCandidatoCursoByIdCandidatoId(id);
        if(candidatoCurso == null){
            return new ModelAndView("redirect:/course/regist?id="+ id);
        }
        ModelAndView mv= new ModelAndView("candidature/list/invoice","fatura", subjectCourseService.getFactura(candidatoCurso));
        mv.addObject("pagamento", new Pagamento());
        return mv;
    }
}
