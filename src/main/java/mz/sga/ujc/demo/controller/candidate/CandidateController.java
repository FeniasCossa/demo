package mz.sga.ujc.demo.controller.candidate;

import mz.sga.ujc.demo.service.candidatuta.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @RequestMapping(path = "/local", method = RequestMethod.GET)
    public ModelAndView local(@RequestParam("redindn-00409-w44500-Join") Integer id){
       return candidateService.getData(id,new ModelAndView("candidate/local"));
    }
    @RequestMapping(path = "/result", method = RequestMethod.GET)
    public ModelAndView result(@RequestParam("redindn-00409-3390d0-Join") Integer id){
        return candidateService.getData(id,new ModelAndView( "candidate/result"));
    }

}