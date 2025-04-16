package mz.sga.ujc.demo.model.restricoes;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// @Embeddable serve para indicar que essa tabela possui uma chave composta.
@Embeddable
public class RealizacaoExamePk implements Serializable{

    @Column(name = "exame_id")
    private Integer exame;

    @Column(name = "candidato_id")
    private Integer candidato;

    @Column(name = "instituicao_id")
    private Integer instituicao;
}
