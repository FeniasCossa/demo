package mz.sga.ujc.demo.controller;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/jasper-report")
@RestController
public class PdfController {

    private final PdfService pdfService;

    @Autowired
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/gerar-pdf")
    public void gerarPdf(@RequestBody Conta conta) throws IOException {
        this.pdfService.gerar(conta);
    }
}


