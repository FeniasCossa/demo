package mz.sga.ujc.demo.controller.candidate;

import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.model.restricoes.PagamentoPK;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.repository.candidatura.PagamentoRepository;
import mz.sga.ujc.demo.service.Info.SmsSender;
import mz.sga.ujc.demo.service.auth.AccountService;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import mz.sga.ujc.demo.service.candidatuta.SubjectCourseService;
import mz.sga.ujc.demo.service.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static mz.sga.ujc.demo.utils.Utilities.SUCCESSFULLYPAYMENT;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final CandidateService candidateService;
    private final PaymentService paymentService;
    private final CandidatoCursoRepository candidatoCursoRepo;
    private final PagamentoRepository pagamentoRepo;
    private final SubjectCourseService subjectCourseService;

    private final AccountService accountService;

    @Autowired
    public PaymentController(CandidateService candidateService, PaymentService paymentService, CandidatoCursoRepository candidatoCursoRepo, PagamentoRepository pagamentoRepo, SubjectCourseService subjectCourseService, AccountService accountService) {
        this.candidateService = candidateService;
        this.paymentService = paymentService;
        this.candidatoCursoRepo = candidatoCursoRepo;
        this.pagamentoRepo = pagamentoRepo;
        this.subjectCourseService = subjectCourseService;
        this.accountService = accountService;
        logger.info("Initializing PaymentController ...");
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView payment(@RequestParam("redindn-00409-0000-Join") Integer id) {
        logger.info("getting payment details by " + id + " candidate number");
        return candidateService.getData(id, new ModelAndView("candidate/payment"));
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView payment(@Valid Pagamento pagamento, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Houve problemas");
        }

        CandidatoCurso candidatoCurso = candidatoCursoRepo.getCandidatoCursoByIdCandidatoId(pagamento.getId().getCandidatoId());
        pagamento.setId(new PagamentoPK(candidatoCurso.getCandidato().getCodigo(), candidatoCurso.getCurso().getId()));
        pagamento.setValor(subjectCourseService.getValor(candidatoCurso.getCurso()));
        pagamento.setCandidato(candidatoCurso.getCandidato());
        pagamento.setEstado("Pago");
        pagamento.setCurso(candidatoCurso.getCurso());
        pagamentoRepo.save(pagamento);
        logger.info("sending sms to candidate ... " + pagamento.getCandidato().getCodigo());
        SmsSender sms = new SmsSender(Long.parseLong(accountService.getAccountByCode(pagamento.getCandidato().getCodigo()).getTelefone()), SUCCESSFULLYPAYMENT);
        Thread thread = new Thread(sms);
        thread.start();
        return payment(pagamento.getCandidato().getCodigo());
    }
}
