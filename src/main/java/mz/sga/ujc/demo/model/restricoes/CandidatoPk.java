package mz.sga.ujc.demo.model.restricoes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.auth.Perfil;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// @Embeddable serve para indicar que essa tabela possui uma chave composta.
@Embeddable
public class CandidatoPk implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
}
