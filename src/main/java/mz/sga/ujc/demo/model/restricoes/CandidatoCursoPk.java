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
public class CandidatoCursoPk implements Serializable{

    @Column(name = "candidato_id")
    private Integer candidatoId;

    @Column(name = "curso_id")
    private Integer cursoId;

    @Column(name = "provincia_id")
    private Integer provinciaId;
}
