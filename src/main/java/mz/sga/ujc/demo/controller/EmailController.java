package mz.sga.ujc.demo.controller;


import mz.sga.ujc.demo.service.auth.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmailController {
    private final EmailService emailService;
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(path = "/email-send/{msg}", method = RequestMethod.GET)
    public ModelAndView sendMail(@PathVariable("msg") String msg) {
        String[] emailAndPsw =msg.split("_");
        emailService.SendEmail(emailAndPsw);
        return new ModelAndView("redirect:/candidato/register?id="+emailAndPsw[0]);
    }
}
