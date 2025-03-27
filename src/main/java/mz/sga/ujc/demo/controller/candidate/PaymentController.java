package mz.sga.ujc.demo.controller.candidate;

import mz.sga.ujc.demo.model.candidatura.Pagamento;
import mz.sga.ujc.demo.service.Info.SmsSender;
import mz.sga.ujc.demo.service.auth.AccountService;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
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

    private final AccountService accountService;
    @Autowired
    public PaymentController(CandidateService candidateService, PaymentService paymentService, AccountService accountService) {
        this.candidateService = candidateService;
        this.paymentService = paymentService;
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
        if (result.hasErrors()) {
            return payment(pagamento.getId().getCandidato().getCodigo());
        }
        logger.info("saving payment detail ... by "+pagamento.getId().getCandidato().getCodigo()+" name: "+pagamento.getId().getCandidato().getNome());
        paymentService.save(pagamento);

        logger.info("sending sms to candidate ... "+ pagamento.getId().getCandidato().getCodigo());
        SmsSender sms= new SmsSender(Long.parseLong(accountService.getAccountByCode(pagamento.getId().getCandidato().getCodigo()).getTelefone()),SUCCESSFULLYPAYMENT);
        Thread thread = new Thread(sms);
        thread.start();

        return payment(pagamento.getId().getCandidato().getCodigo());
    }
}
