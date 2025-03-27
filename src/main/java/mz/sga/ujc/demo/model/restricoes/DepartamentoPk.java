package mz.sga.ujc.demo.model.restricoes;

import lombok.*;
import mz.sga.ujc.demo.model.exame.Instituicao;

import javax.persistence.Column;
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
public class DepartamentoPk implements Serializable {

    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;
}
