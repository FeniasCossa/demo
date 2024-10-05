package mz.sga.ujc.demo.model.parametrization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.sga.ujc.demo.model.AbstractEntity;
import mz.sga.ujc.demo.model.candidatura.Candidato;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "Escola{}";
    }
}
