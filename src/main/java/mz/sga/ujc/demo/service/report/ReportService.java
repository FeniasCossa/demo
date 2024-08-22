package mz.sga.ujc.demo.service.report;

import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.service.paramentrization.ProvinceService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    ProvinceService provinceService;


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path="C:\\jasperReport";
        List<Provincia> list = provinceService.provinceList();

        //load and compile files
        File file= ResourceUtils.getFile("classPath:candidato.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource ds= new JRBeanCollectionDataSource(list);

        Map<String,Object> has= new HashMap<>();
        has.put("created By","Fenias Cossa");

        JasperPrint jp = JasperFillManager.fillReport(jr,has,ds);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jp,path+"\\candidatos.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToHtmlFile(jp,path+"\\candidatos.pdf");
        }
        return "File Created At path: "+path;
    }
}
