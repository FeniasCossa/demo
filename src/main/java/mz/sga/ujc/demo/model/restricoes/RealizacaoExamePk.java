package mz.sga.ujc.demo.model.restricoes;

import lombok.*;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.exame.Exame;
import mz.sga.ujc.demo.model.exame.Sala;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// @Embeddable serve para indicar que essa tabela possui uma chave composta.
@Embeddable
public class RealizacaoExamePk implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "exame_id")
    private Exame exame;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "sala_id", referencedColumnName = "id"),
            @JoinColumn(name = "instituicao_id", referencedColumnName = "instituicao_id"),
            @JoinColumn(name = "departamento_id", referencedColumnName = "departamento_id")
    })
    private Sala sala;
}
