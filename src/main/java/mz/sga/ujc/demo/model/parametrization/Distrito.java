
package mz.sga.ujc.demo.model.parametrization;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@Table(name = "distrito")
public class Distrito extends AbstractEntity<Integer>{
    
    @Column(name="name")
    private String name;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Provincia provincia;
    
}
