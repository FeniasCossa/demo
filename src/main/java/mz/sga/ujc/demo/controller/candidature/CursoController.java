package mz.sga.ujc.demo.controller.candidature;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.model.restricoes.PagamentoPK;
import mz.sga.ujc.demo.repository.candidatura.*;
import mz.sga.ujc.demo.service.candidatuta.DisciplinaCursoService;
import mz.sga.ujc.demo.service.paramentrization.ProvinciaService;
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

import javax.validation.Valid;

@Controller
@RequestMapping("/course")
public class CursoController {
    private final ProvinciaService provinciaService;
    private final CursoRepository cursoRepository;
    private final CandidatoCursoRepository candidatoCursoRepository;
    private final DisciplinaCursoRepository disciplinaCursoRepository;
    private final CandidatoRepository candidatoRepository;
    private final DisciplinaCursoService disciplinaCursoService;
    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public CursoController(ProvinciaService provinciaService, CursoRepository cursoRepository, CandidatoCursoRepository candidatoCursoRepository, DisciplinaCursoRepository disciplinaCursoRepository, DisciplinaCursoService disciplinaCursoService, CandidatoRepository candidatoRepository, PagamentoRepository pagamentoRepository) {
        this.provinciaService = provinciaService;
        this.cursoRepository = cursoRepository;
        this.candidatoCursoRepository = candidatoCursoRepository;
        this.disciplinaCursoRepository = disciplinaCursoRepository;
        this.disciplinaCursoService = disciplinaCursoService;
        this.candidatoRepository = candidatoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    @RequestMapping(path = "/regist", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getCourses(@RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView("candidature/course/create");
        mv.addObject("candidatoCurso", new CandidatoCurso());
        mv.addObject("provincias", provinciaService.listaProvincias());
        mv.addObject("codigo", id);
        mv.addObject("cursos", cursoRepository.findAll());
        mv.addObject("taxa", disciplinaCursoService.DistinctTotalByCurso());
        mv.addObject("disciplinasCursos", disciplinaCursoRepository.findAll());
        return mv;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView saveCourse(@Valid CandidatoCurso candidatoCurso, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv.addObject(candidatoCurso.getId().getCandidato());
            mv.setViewName("redirect:/course/regist");
        }
        mv.setViewName("redirect:/course/fatura");
        Pagamento pagamento = new Pagamento();
        pagamento.setId(new PagamentoPK(candidatoCurso.getId().getCandidato(), candidatoCurso.getId().getCurso()));
        pagamento.setValor(disciplinaCursoService.getValor(candidatoCurso.getId().getCurso()));
        pagamento.setEstado("Nao Pago");
        candidatoCursoRepository.save(candidatoCurso);
        pagamentoRepository.save(pagamento);
        attributes.addFlashAttribute("candidato", candidatoRepository.getReferenceById(candidatoCurso.getId().getCandidato().getCodigo()));
        mv.addObject("candidato", candidatoCurso.getId().getCandidato());
        return mv;
    }

    @RequestMapping(path = "/fatura", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getFatura(@RequestParam("candidato") Integer id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("candidature/list/invoice");
        Candidato candidato = candidatoRepository.getReferenceById(id);
        Pagamento pagamento = pagamentoRepository.getPagamentoById_Candidato(candidato);
        if(pagamento == null){
            attributes.addFlashAttribute("id","");
            mv.addObject("id",candidato.getCodigo());
            mv.setViewName("redirect:/course/regist");
        }else {
            mv.addObject("fatura", disciplinaCursoService.getFactura(pagamento));
        }
        return mv;
    }
}
