package mz.sga.ujc.demo.model.restricoes;

import lombok.*;
import mz.sga.ujc.demo.model.exame.Departamento;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// @Embeddable serve para indicar que essa tabela possui uma chave composta.
@Embeddable
public class SalaPk implements Serializable {

    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "departamento_id", referencedColumnName = "id"),
            @JoinColumn(name = "instituicao_id", referencedColumnName = "instituicao_id")
    })
    private Departamento departamento;
}
