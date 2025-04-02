package mz.sga.ujc.demo.controller.admin;

import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import mz.sga.ujc.demo.service.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);


    private final CandidateService candidateService;
    private final PaymentService paymentService;
    @Autowired
    public AdminController(CandidateService candidateService, PaymentService paymentService) {
        this.candidateService = candidateService;
        this.paymentService = paymentService;
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
        System.out.println(candidateService.CountAllByDay());
        return mv;
    }
}
