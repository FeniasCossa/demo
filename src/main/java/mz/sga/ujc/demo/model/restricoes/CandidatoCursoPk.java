package mz.sga.ujc.demo.model.restricoes;

import lombok.*;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.Curso;
import mz.sga.ujc.demo.model.parametrization.Provincia;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// @Embeddable serve para indicar que essa tabela possui uma chave composta.
@Embeddable
public class CandidatoCursoPk implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;
}
