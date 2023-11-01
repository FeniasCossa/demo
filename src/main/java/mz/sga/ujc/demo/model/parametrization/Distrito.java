
package mz.sga.ujc.demo.model.parametrization;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.restricoes.DistritoPK;

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
public class Distrito{
    
    @EmbeddedId
    private DistritoPK id;

    @Column(name="nome")
    private String nome;    

    @OneToMany(mappedBy = "residencia")
    private List<Candidato> candidatos;
}
