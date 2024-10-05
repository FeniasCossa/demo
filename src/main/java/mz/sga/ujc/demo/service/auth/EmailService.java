package mz.sga.ujc.demo.service.auth;

import com.sun.activation.registries.LogSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static mz.sga.ujc.demo.utils.Utilities.*;
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final HtmlEmailText htmlEmailText;
    @Autowired
    public EmailService(JavaMailSender mailSender, HtmlEmailText htmlEmailText) {
        this.mailSender = mailSender;
        this.htmlEmailText = htmlEmailText;
    }

    public void SendEmail(String[]  emailAndPsw){
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( emailAndPsw[1] );
            helper.setSubject(SUBJECT_EMAIL);
            helper.setText(htmlEmailText.EmailText(emailAndPsw[0]), true);
            mailSender.send(mail);
            } catch (MessagingException mx) {
            LogSupport.log("Error to sent email for: "+ emailAndPsw[1]);
        }
    }
}
