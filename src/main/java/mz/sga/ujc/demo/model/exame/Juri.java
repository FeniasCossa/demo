package mz.sga.ujc.demo.model.exame;

import lombok.Getter;
import lombok.Setter;
import mz.sga.ujc.demo.model.AbstractEntity;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.Curso;
import mz.sga.ujc.demo.model.parametrization.Provincia;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Juri extends AbstractEntity<Long> {

    private String numero;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @OneToMany(mappedBy = "juri")
    private List<Candidato> candidatos;
}
