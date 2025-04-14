package mz.sga.ujc.demo.model.exame;

import lombok.*;
import mz.sga.ujc.demo.model.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Sala extends AbstractEntity<Long> {

    @Column(name = "nome")
    private String nome;

    @Column(name = "capacidade")
    private int capacidade; // Capacidade máxima de candidatos

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

}
