package mz.sga.ujc.demo.model.candidatura;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "disciplina")
public class Disciplina extends AbstractEntity<Integer>{
    
    private String nome;

    @ManyToOne
    @JoinColumn(name = "taxa_id")
    private Taxa taxa;

}
