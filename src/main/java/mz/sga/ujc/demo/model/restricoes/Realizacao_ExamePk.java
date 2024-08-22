package mz.sga.ujc.demo.model.restricoes;

import lombok.*;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.exame.Exame;
import mz.sga.ujc.demo.model.parametrization.Escola;
import mz.sga.ujc.demo.model.parametrization.Provincia;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// @Embeddable serve para indicar que essa tabela possui uma chave composta.
@Embeddable
public class Realizacao_ExamePk implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "exame_id")
    private Exame exame;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;
}
