package mz.sga.ujc.demo.controller;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.service.auth.ContaService;
import mz.sga.ujc.demo.service.auth.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.internet.MimeMessage;

@RestController
public class EmailController {

    private final JavaMailSender mailSender;
    private final ContaService contaService;
    private final EmailService emailService;

    @Autowired
    public EmailController(JavaMailSender mailSender, ContaService contaService, EmailService emailService) {
        this.mailSender = mailSender;
        this.contaService = contaService;
        this.emailService = emailService;
    }

    @RequestMapping(path = "/email-send/{msg}", method = RequestMethod.GET)
    public ModelAndView sendMail(@PathVariable("msg") String msg, RedirectAttributes attributes) {
        ModelAndView mv= new ModelAndView();
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            String[] emailAndPsw =msg.split("_");
            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( emailAndPsw[1] );
            helper.setSubject( "Ola, Bem vindo!" );
            helper.setText(emailService.EmailText(emailAndPsw[0]), true);
            mailSender.send(mail);
            Conta conta=contaService.getContaByCodigo(Integer.valueOf(emailAndPsw[0]));
            mv.addObject("id", conta);
            attributes.addFlashAttribute("success", "O seu codigo é " + conta.getCodigo() + " grave o seu condigo para usares quando for a fazer login");
            mv.setViewName("redirect:/candidato/register");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}
