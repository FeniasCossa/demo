package mz.sga.ujc.demo.model.restricoes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.parametrization.Provincia;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// @Embeddable serve para indicar que essa tabela possui uma chave composta.
@Embeddable
public class DistritoPK implements Serializable{
    
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;
}
