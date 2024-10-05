package mz.sga.ujc.demo.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class Utilities {

    public static final String SUBJECT_EMAIL="UJC - Candidatura!";
    public static final String EMAILEXISTS = "Este email ja foi cadastrado, por favor faça login, verifique na caixa do email o codigo de candidatura.";
    public static final String NUITEXISTS = "Este Nuit ja foi cadastrado, por favor faça login, verifique na caixa do email o codigo de candidatura.";
    public static final String SUCCESSFULLYPAYMENT = "Pre-registo pago com sucesso, agora aguarde alguns dias, iremos informar o local brevemente";


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
}
