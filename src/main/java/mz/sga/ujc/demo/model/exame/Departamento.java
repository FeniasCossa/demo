package mz.sga.ujc.demo.model.exame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.sga.ujc.demo.model.restricoes.DepartamentoPk;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departamento")
public class Departamento {

    @EmbeddedId
    private DepartamentoPk id;

    @Column(name = "nome")
    private String nome;
}
