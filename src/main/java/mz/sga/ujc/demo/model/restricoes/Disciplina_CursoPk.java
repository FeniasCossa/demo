package mz.sga.ujc.demo.model.restricoes;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.candidatura.Curso;
import mz.sga.ujc.demo.model.candidatura.Disciplina;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// @Embeddable serve para indicar que essa tabela possui uma chave composta.
@Embeddable
public class Disciplina_CursoPk implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="disciplina_id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
