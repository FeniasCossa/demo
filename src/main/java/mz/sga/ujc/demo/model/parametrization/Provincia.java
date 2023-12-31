
package mz.sga.ujc.demo.model.parametrization;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.AbstractEntity;

/**
 *
 * @author Fenias Cossa
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="provincia")
public class Provincia extends AbstractEntity<Integer>{
    
    @Column(name="nome")
    private String nome;

    @OneToMany(mappedBy = "provincia")
    private List<Escola> escolas;
}