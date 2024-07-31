package mz.sga.ujc.demo.controller.candidate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CandidateController {

    @RequestMapping(path = "/fatura", method = RequestMethod.GET)
    public String getFactua() {
        return "candidature/list/invoice";
    }

    @RequestMapping(path = "/payment", method = RequestMethod.GET)
    public String payment(){
        return "candidate/payment";
    }
    @RequestMapping(path = "/local", method = RequestMethod.GET)
    public String local(){
        return "candidate/local";
    }
    @RequestMapping(path = "/result", method = RequestMethod.GET)
    public String result(){
        return "candidate/result";
    }
}