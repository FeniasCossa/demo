package mz.sga.ujc.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class ClassRoomDistrController {


        @RequestMapping(value = "/classroom", method = RequestMethod.GET)
        public String mostrarGrafico(Model model) {
            model.addAttribute("conda","Sou eu");
            return "/admin/sala/index.html"; // Nome do template Thymeleaf
        }
}
