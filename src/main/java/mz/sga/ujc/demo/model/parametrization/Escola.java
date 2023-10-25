package mz.sga.ujc.demo.model.parametrization;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name = "escola")
public class Escola extends AbstractEntity<Integer>{
    
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    private Provincia provincia;
}
