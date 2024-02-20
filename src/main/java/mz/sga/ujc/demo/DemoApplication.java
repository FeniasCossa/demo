package mz.sga.ujc.demo;

import mz.sga.ujc.demo.model.candidatura.Curso;
import mz.sga.ujc.demo.model.candidatura.Disciplina;
import mz.sga.ujc.demo.model.candidatura.DisciplinaCurso;
import mz.sga.ujc.demo.model.restricoes.DisciplinaCursoPk;
import mz.sga.ujc.demo.repository.candidatura.DisciplinaCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
