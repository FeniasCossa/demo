package mz.sga.ujc.demo.utils;

import mz.sga.ujc.demo.model.candidatura.Factura;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utilities {

    public static final Logger LOGGER = LoggerFactory.getLogger(Utilities.class);
    public static final String SUBJECT_EMAIL="UJC - Candidatura!";
    public static final String EMAILEXISTS = "Este email ja foi cadastrado, por favor faça login, verifique na caixa do email o codigo de candidatura.";
    public static final String NUITEXISTS = "Este Nuit ja foi cadastrado, por favor faça login, verifique na caixa do email o codigo de candidatura.";
    public static final String SUCCESSFULLYPAYMENT = "Pre-registo pago com sucesso, agora aguarde alguns dias, iremos informar o local brevemente";
    public static final String IMG = "classpath:jasper/img/ujc.png";

    public byte[] loadImage(String imgPath) throws IOException {
        String image = ResourceUtils.getFile(imgPath).getAbsolutePath();
        File file = new File(image);
        try (InputStream inputStream = new FileInputStream(file)){
            return IOUtils.toByteArray(inputStream);
        }
    }

    public String getSaveDirectory(String PDFName, String destino) {
        this.createDeirectory(destino);
        return destino+PDFName.concat(".pdf");
    }

    private void createDeirectory(String name) {
        File dir = new File(name);
        if (!dir.exists()) dir.mkdir();
    }

    public String getAbsolutePath(String filePath) throws FileNotFoundException {
        return ResourceUtils.getFile(filePath).getAbsolutePath();
    }

    public void gerar(Factura factura, HttpServletResponse response, String JasperFilePath, String destino, String filename) throws IOException {
        byte[] image = loadImage(IMG);
        System.out.println(factura.toString());
        LOGGER.info("Map all info to invoice detail");
        Map<String, Object> params = new HashMap<>();
        params.put("codigo", factura.getCodigo());
        params.put("createdAt", factura.getData_registo());
        params.put("nome",factura.getNome());
        params.put("curso", factura.getCurso());
        params.put("imgJasper", image);
        params.put("entidade", factura.getEntidade());
        params.put("referencia", factura.getReferencia());
        params.put("banco", factura.getBank());
        params.put("valor",factura.getValor());
        params.put("dataPagamento",factura.getDataPagamento());
        try {
            String absolutePath= getAbsolutePath(JasperFilePath);

            String folder= getSaveDirectory(filename+new Date().getTime(),destino);

            JasperReport report = JasperCompileManager.compileReport(absolutePath);
            LOGGER.info("Report compiled: "+absolutePath);
            JasperPrint print = JasperFillManager.fillReport(report,params, new JREmptyDataSource());
            LOGGER.info("Jasper print");
            JasperExportManager.exportReportToPdfFile(print, folder);
            LOGGER.info("PDF exported to: {}", folder);

            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput(new SimpleExporterInput(print));
            ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
            pdfExporter.exportReport();

            // Configura a respota para o tipo PDF
            LOGGER.info("config response to PDF");
            response.setContentType("application/pdf");

            // Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
            // para fazer download do relatório troque 'inline' por 'attachment'
            LOGGER.info("Define the file to visualize into browser");
            response.setHeader("Content-Disposition", "inline; filename=factura"+factura.getCodigo()+".pdf");

            // Faz a exportação do relatório para o HttpServletResponse
            LOGGER.info("Export report to HttpServletResponse");
            OutputStream responseOutStream = response.getOutputStream();
            responseOutStream.write(pdfReportStream.toByteArray());
            responseOutStream.close();
            pdfReportStream.close();


        } catch (JRException e) {
            throw new RuntimeException(e);
        }

    }
}
