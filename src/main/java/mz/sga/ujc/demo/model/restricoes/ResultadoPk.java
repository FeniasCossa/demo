package mz.sga.ujc.demo.model.restricoes;

import lombok.*;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.exame.Exame;

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
public class ResultadoPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "exame_id")
    private Exame exame;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

}
