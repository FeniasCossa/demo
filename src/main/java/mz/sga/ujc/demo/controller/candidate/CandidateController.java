package mz.sga.ujc.demo.controller.candidate;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.exame.RealizacaoExame;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.repository.candidatura.RealizacaoExameRepository;
import mz.sga.ujc.demo.service.auth.AccountService;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import static mz.sga.ujc.demo.utils.Utilities.*;

@Controller
public class CandidateController {

    private static Logger logger = LoggerFactory.getLogger(CandidateController.class);
    private final CandidateService candidateService;
    private final RealizacaoExameRepository realizacaoExameRepository;
    private final CandidatoCursoRepository candidatoCursoRepository;
    private final AccountService accountService;

    @Autowired
    public CandidateController(CandidateService candidateService, RealizacaoExameRepository realizacaoExameRepository, CandidatoCursoRepository candidatoCursoRepository, AccountService accountService) {
        this.candidateService = candidateService;
        this.realizacaoExameRepository = realizacaoExameRepository;
        this.candidatoCursoRepository = candidatoCursoRepository;
        this.accountService = accountService;
        logger.info("Initializing CandidateController ...");
    }

    @RequestMapping(path = "/local", method = RequestMethod.GET)
    public ModelAndView local(@RequestParam("redindn-00409-w44500-Join") Integer id) {
        ModelAndView mv = new ModelAndView("candidate/local");
        return getModelAndView(id, mv,MESSAGE_JURI_NOTFOUND);
    }

    @RequestMapping(path = "/result", method = RequestMethod.GET)
    public ModelAndView result(@RequestParam("redindn-00409-3390d0-Join") Integer id) {
        ModelAndView mv = new ModelAndView("candidate/result");
        return getModelAndView(id, mv,MESSAGE_RESULT_NOTFOUND);
    }

    @NotNull
    private ModelAndView getModelAndView(Integer id, ModelAndView mv,String messege) {
        Candidato candidato = candidateService.getCandidateByCode(id);
        List<RealizacaoExame> realizacaoExame = realizacaoExameRepository.getRealizacaoExameByCandidato(candidato);
        CandidatoCurso candidatoCurso = candidatoCursoRepository.getCandidatoCursoByIdCandidatoId(id);
        mv.addObject("candidatoCurso", candidatoCurso);
        mv.addObject("candidato", candidato);
        mv.addObject("userlogado", accountService.getAccountByCode(id));
        if (realizacaoExame.isEmpty()) {
            mv.addObject("mensagem", messege);
        } else {
            RealizacaoExame examesUnicos = realizacaoExame.get(0);
            mv.addObject("realizacaoExame", realizacaoExame);
            mv.addObject("examesUnicos", examesUnicos);
        }
        return mv;
    }

}