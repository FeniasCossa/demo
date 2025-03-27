package mz.sga.ujc.demo.model.exame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.sga.ujc.demo.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instituicao")
public class Instituicao extends AbstractEntity<Integer> {

    @Column(name = "nome")
    private String nome;
}
