package mz.sga.ujc.demo.controller.candidature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/fatura")
public class PagamentoController {


    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getFactua(){
        return "candidature/list/invoice";
    }
    
}