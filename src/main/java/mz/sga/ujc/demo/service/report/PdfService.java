package mz.sga.ujc.demo.service.report;

import mz.sga.ujc.demo.model.candidatura.Factura;
import mz.sga.ujc.demo.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class PdfService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PdfService.class);
    public static final String PATHFACTURAJASPER = "classpath:jasper/factura/facturaPdf.jrxml";
    public static final String pathPagamento = "classpath:jasper/factura/pdf.jrxml";
    public static final String DESTINO ="C:\\Jasper-Report\\";


    Utilities utilities = new Utilities();

   public void generateInvoice(Factura factura, HttpServletResponse response) throws IOException {
       utilities.gerar(factura,response,PATHFACTURAJASPER,DESTINO,"factura_created");
       LOGGER.info("Factura criada com sucesso");
   }

    public void generatePaymentDetail(Factura factura, HttpServletResponse response) throws IOException {
        utilities.gerar(factura,response,pathPagamento,DESTINO,"comprovativo");
        LOGGER.info("Comprovativo do pagamento criada com sucesso");
    }
}
