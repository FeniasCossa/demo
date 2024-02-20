package mz.sga.ujc.demo.controller.candidature;

import mz.sga.ujc.demo.model.candidatura.Curso;
import mz.sga.ujc.demo.model.candidatura.Disciplina;
import mz.sga.ujc.demo.model.candidatura.DisciplinaCurso;
import mz.sga.ujc.demo.model.restricoes.DisciplinaCursoPk;
import mz.sga.ujc.demo.repository.candidatura.CursoRepository;
import mz.sga.ujc.demo.repository.candidatura.DisciplinaCursoRepository;
import mz.sga.ujc.demo.repository.candidatura.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("diciplina-curso")
public class DisciplinaCursoController {

    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String get(){
        return "html";
    }
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(DisciplinaCurso disciplinaCurso){
        Disciplina d=disciplinaRepository.getReferenceById(1);
        Curso c= cursoRepository.getReferenceById(1);
        DisciplinaCurso cd=new DisciplinaCurso();
        cd.setId(new DisciplinaCursoPk(d,c));
        cd.setPesoDisciplina(30F);
        disciplinaCursoRepository.save(cd);
        return "redirect:/course/fatura";
    }
}
