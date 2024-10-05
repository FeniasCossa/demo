package mz.sga.ujc.demo.model.restricoes;

import lombok.*;
import mz.sga.ujc.demo.model.candidatura.Candidato;

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
public class PagamentoPK implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

}
