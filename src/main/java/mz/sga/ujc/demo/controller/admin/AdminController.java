package mz.sga.ujc.demo.controller.admin;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.exame.Instituicao;
import mz.sga.ujc.demo.repository.candidatura.InstituicaoRepository;
import mz.sga.ujc.demo.repository.candidatura.PagamentoRepository;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import mz.sga.ujc.demo.service.candidatuta.JuriService;
import mz.sga.ujc.demo.service.paramentrization.ProvinceService;
import mz.sga.ujc.demo.service.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    private final CandidateService candidateService;
    private final PaymentService paymentService;
    private final InstituicaoRepository instituicaoRepo;
    private final ProvinceService provinceService;
    private final JuriService juriService;
    private final PagamentoRepository pagamentoRepository;
    @Autowired
    public AdminController(CandidateService candidateService, PaymentService paymentService, InstituicaoRepository instituicaoRepo, ProvinceService provinceService, JuriService juriService, PagamentoRepository pagamentoRepository) {
        this.candidateService = candidateService;
        this.paymentService = paymentService;
        this.instituicaoRepo = instituicaoRepo;
        this.provinceService = provinceService;
        this.juriService = juriService;
        this.pagamentoRepository = pagamentoRepository;
    }

    @RequestMapping(path = "/home/{codigo}", method = RequestMethod.GET)
    public ModelAndView Adminhome(@PathVariable("codigo") Integer code) {
        LOGGER.info("Getting info for code - {} to getting Home page ... ",code);
        ModelAndView mv=new ModelAndView("admin/home/index");
        mv.addObject("count",candidateService.CountAll());
        mv.addObject("provenence",candidateService.listProvinceAndQuantity());
        mv.addObject("semPagamentos",paymentService.candidatosSemPagamentos());
        mv.addObject("comPagamentos",paymentService.candidatosComPagamentos());
        mv.addObject("allpaymant",paymentService.getAllPaymant());
        mv.addObject("candidateByDate",candidateService.CountAllByDay());
        return mv;
    }

    @RequestMapping(path = "/candidates", method = RequestMethod.GET)
    public String classRoom(Model model) {
        List<Candidato> candidates = candidateService.listCandidato();
        model.addAttribute("candidates",candidates);
        return "admin/candidates/index";
    }

    @RequestMapping(path = "/class", method = RequestMethod.GET)
    public String classAndSchool(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 7, Sort.by("id").ascending());
        Page<Instituicao> institutes = instituicaoRepo.findAll(pageable);
        model.addAttribute("instituicoes",institutes);
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", institutes.getTotalPages());
        System.out.println(institutes);
        return "admin/class/index";
    }

    @RequestMapping(path = "/registerClass", method = RequestMethod.GET)
    public String registerClass(Model model){
        model.addAttribute("provincias", provinceService.provinceList());
        model.addAttribute("instituicao", new Instituicao());
        return "admin/class/registerClass";
    }

    @RequestMapping(path = "/institute/save", method = RequestMethod.POST)
    public String saveInstitution(@Valid Instituicao instituicao, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/adimn/registerClass";
        }
        instituicaoRepo.save(instituicao);
        return "redirect:/admin/class";
    }

    @RequestMapping(path = "/gerarJuris", method = RequestMethod.POST)
    public ModelAndView gerarJuris(){
        ModelAndView mv= new ModelAndView("admin/candidates/index");

        LOGGER.info("Atribuindo os Juris em Segundo Plano");
        Thread thread =new Thread(juriService);
        thread.start();
        return mv;
    }

}
