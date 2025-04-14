package mz.sga.ujc.demo.controller.candidate;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.model.restricoes.PagamentoPK;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
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
    private final SubjectCourseService subjectCourseService;

    private final AccountService accountService;
    @Autowired
    public PaymentController(CandidateService candidateService, PaymentService paymentService, CandidatoCursoRepository candidatoCursoRepo, SubjectCourseService subjectCourseService, AccountService accountService) {
        this.candidateService = candidateService;
        this.paymentService = paymentService;
        this.candidatoCursoRepo = candidatoCursoRepo;
        this.subjectCourseService = subjectCourseService;
        this.accountService = accountService;
        logger.info("Initializing PaymentController ...");
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView payment(@RequestParam("redindn-00409-0000-Join") Integer id){
        logger.info("getting payment details by "+id+" candidate number");
        return candidateService.getData(id,new ModelAndView("candidate/payment"));
    }
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView payment(@Valid Pagamento pagamento, BindingResult result){
       if(result.hasErrors()){
           logger.info("failed to save payment detail ... by "+pagamento.getCandidato().getCodigo()+" name: "+pagamento.getCandidato().getNome());
       }
        logger.info("saving payment detail ... by "+pagamento.getCandidato().getCodigo()+" name: "+pagamento.getCandidato().getNome());
        return getModelAndView(pagamento);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ModelAndView updatePayment(@Valid Pagamento pagamento, BindingResult result){
        if(result.hasErrors()){
            logger.info("failed to update payment detail ... by "+pagamento.getCandidato().getCodigo()+" name: "+pagamento.getCandidato().getNome());
        }
        logger.info("updating payment detail ... by "+pagamento.getCandidato().getCodigo()+" name: "+pagamento.getCandidato().getNome());
        pagamento.setEstado("Pago");
        return getModelAndView(pagamento);
    }

    private ModelAndView getModelAndView(@Valid Pagamento pagamento) {
        Candidato candidato = candidateService.getCandidateByCode(pagamento.getCandidato().getCodigo());
        CandidatoCurso candidatoCurso= candidatoCursoRepo.getCandidatoCursoByIdCandidatoId(candidato.getCodigo());
        pagamento.setId(new PagamentoPK(candidato.getCodigo(),candidatoCurso.getCurso().getId()));
        pagamento.setValor(subjectCourseService.getValor(candidatoCurso.getCurso()));
        pagamento.setCandidato(candidato);
        pagamento.setEstado("Pago");
        paymentService.save(pagamento);
        logger.info("sending sms to candidate ... "+ pagamento.getCandidato().getCodigo());
        SmsSender sms= new SmsSender(Long.parseLong(accountService.getAccountByCode(pagamento.getCandidato().getCodigo()).getTelefone()),SUCCESSFULLYPAYMENT);
        Thread thread = new Thread(sms);
        thread.start();
        return payment(pagamento.getCandidato().getCodigo());
    }
}
