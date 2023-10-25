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
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.Curso;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString

// @Embeddable serve para indicar que essa tabela possui uma chave composta.
@Embeddable
public class PagamentoPK implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
