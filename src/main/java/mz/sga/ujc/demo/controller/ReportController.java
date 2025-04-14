package mz.sga.ujc.demo.controller;

import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import mz.sga.ujc.demo.service.candidatuta.SubjectCourseService;
import mz.sga.ujc.demo.service.payment.PaymentService;
import mz.sga.ujc.demo.service.report.PdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/jasper-report")
@Controller
public class ReportController {

    private static Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final PdfService pdfService;
    private final PaymentService paymentService;
    private final SubjectCourseService subjectCourseService;
    private final CandidateService candidateService;
    private final CandidatoCursoRepository candidatoCursoRepo;
    @Autowired
    public ReportController(PdfService pdfService, PaymentService paymentService, SubjectCourseService subjectCourseService, CandidateService candidateService, CandidatoCursoRepository candidatoCursoRepo) {
        this.pdfService = pdfService;
        this.paymentService = paymentService;
        this.subjectCourseService = subjectCourseService;
        this.candidateService = candidateService;
        this.candidatoCursoRepo = candidatoCursoRepo;
        logger.info("Initializing ReportController ...");
    }

    @PostMapping("/factura/pdf/{codigo}")
    public void gerarFactura(@PathVariable("codigo") Integer id, HttpServletResponse response) throws IOException {
        CandidatoCurso candidatoCurso= candidatoCursoRepo.getCandidatoCursoByIdCandidatoId(id);
        this.pdfService.generateInvoice(subjectCourseService.getFactura(candidatoCurso),response);
    }

    @PostMapping("/payment/pdf/{codigo}")
    public void gerarComprovativoPagamento(@PathVariable("codigo") Integer id, HttpServletResponse response) throws IOException {
        CandidatoCurso candidatoCurso= candidatoCursoRepo.getCandidatoCursoByIdCandidatoId(id);
        this.pdfService.generatePaymentDetail(subjectCourseService.getFactura(candidatoCurso),response);
    }
}


