package mz.sga.ujc.demo.controller;


import mz.sga.ujc.demo.service.Info.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmailController {
    private final EmailService emailService;
    private static Logger logger = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
        logger.info("Initializing EmailController ... ");
    }

    @RequestMapping(path = "/email-send/{msg}", method = RequestMethod.GET)
    public ModelAndView sendMail(@PathVariable("msg") String msg) {
        String[] emailAndPsw =msg.split("_");
        emailService.SendEmail(emailAndPsw);
        return new ModelAndView("redirect:/candidato/register?id="+emailAndPsw[0]);
    }
}
