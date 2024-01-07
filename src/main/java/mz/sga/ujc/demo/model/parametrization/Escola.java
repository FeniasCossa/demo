package mz.sga.ujc.demo.model.parametrization;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.AbstractEntity;
import mz.sga.ujc.demo.model.candidatura.Candidato;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "escola")
public class Escola extends AbstractEntity<Integer>{
    
    @ManyToOne
    @JoinColumn(name="candidato_id")
    private Candidato candidato;

    private String nome;

    @Column(name = "ano_conclusao")
    private Short anoConclusao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Provincia provincia;
}
