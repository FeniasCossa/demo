package mz.sga.ujc.demo.controller.candidature;

import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.repository.candidatura.CursoRepository;
import mz.sga.ujc.demo.repository.candidatura.DisciplinaCursoRepository;
import mz.sga.ujc.demo.service.candidatuta.CandidateCourceService;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import mz.sga.ujc.demo.service.candidatuta.SubjectCourseService;
import mz.sga.ujc.demo.service.paramentrization.ProvinceService;
import mz.sga.ujc.demo.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/course")
public class CursoController {
    private final ProvinceService provinceService;
    private final CursoRepository cursoRepository;
    private final CandidateCourceService candidateCourceService;
    private final DisciplinaCursoRepository disciplinaCursoRepository;
    private final SubjectCourseService subjectCourseService;
    private final PaymentService paymentService;
    private final CandidateService candidateService;

    @Autowired
    public CursoController(ProvinceService provinceService, CursoRepository cursoRepository, DisciplinaCursoRepository disciplinaCursoRepository, SubjectCourseService subjectCourseService,  CandidateCourceService candidateCourceService, PaymentService paymentService, CandidateService candidateService) {
        this.provinceService = provinceService;
        this.cursoRepository = cursoRepository;
        this.candidateCourceService = candidateCourceService;
        this.disciplinaCursoRepository = disciplinaCursoRepository;
        this.subjectCourseService = subjectCourseService;
        this.candidateService = candidateService;
        this.paymentService = paymentService;
    }

    @RequestMapping(path = "/regist", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getCourses(@RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView("candidature/course/create");
        mv.addObject("candidatoCurso", new CandidatoCurso());
        mv.addObject("provincias", provinceService.provinceList());
        mv.addObject("codigo", id);
        mv.addObject("cursos", cursoRepository.findAll());
        mv.addObject("taxa", subjectCourseService.DistinctTotalByCource());
        mv.addObject("disciplinasCursos", disciplinaCursoRepository.findAll());
        return mv;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView saveCourse(@Valid CandidatoCurso candidatoCurso, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/course/regist?id="+candidatoCurso.getId().getCandidato().getCodigo());
        }
        candidateCourceService.save(candidatoCurso);
        return new ModelAndView("redirect:/course/fatura?candidato="+candidatoCurso.getId().getCandidato().getCodigo());
    }

    @RequestMapping(path = "/fatura", method = RequestMethod.GET)
    public ModelAndView getFatura(@RequestParam("candidato") Integer id) {
        Pagamento pagamento = paymentService.getPaymentByCandidate(candidateService.getCandidateByCode(id));
        if(pagamento == null){
            return new ModelAndView("redirect:/course/regist?id="+ candidateService.getCandidateByCode(id).getCodigo());
        }
        return new ModelAndView("candidature/list/invoice","fatura", subjectCourseService.getFactura(pagamento));
    }
}
