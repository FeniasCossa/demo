package mz.sga.ujc.demo.model.candidatura;

import javax.persistence.Entity;
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
@Table(name = "taxa")
public class Taxa extends AbstractEntity<Integer>{
    
    private String tipo;
    private double valor;
}
