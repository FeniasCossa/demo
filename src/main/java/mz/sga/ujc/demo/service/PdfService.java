package mz.sga.ujc.demo.service;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.utils.Utilities;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class PdfService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PdfService.class);
    public static final String PATHFACTURA = "classpath:jasper/factura/";
    public static final String IMG = "classpath:jasper/img/ujc.png";
    public static final String ARQUIVO = "pdf.jrxml";
    public static final String DESTINO ="C:\\Jasper-Report\\";
    Utilities utilities = new Utilities();

    public void gerar(Conta conta) throws IOException {
        byte[] image = utilities.loadImage(IMG);
        System.out.println(conta.toString());
        Map<String, Object> params = new HashMap<>();
        params.put("codigo", conta.getCodigo());
        params.put("createdAt", conta.getCreatedAt());
        params.put("imgJasper", image);

        try {
            String absolutePath= utilities.getAbsolutePath(PATHFACTURA+ARQUIVO);

            String folder= utilities.getSaveDirectory("factura-created",DESTINO);

            JasperReport report = JasperCompileManager.compileReport(absolutePath);
            LOGGER.info("Report compiled: "+absolutePath);
            JasperPrint print = JasperFillManager.fillReport(report,params, new JREmptyDataSource());
            LOGGER.info("Jasper print");
            JasperExportManager.exportReportToPdfFile(print, folder);
            JasperExportManager.exportReportToPdf(print);
            LOGGER.info("PDF exported to: {}", folder);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

    }

}
