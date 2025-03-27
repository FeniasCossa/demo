package mz.sga.ujc.demo.model.parametrization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.sga.ujc.demo.model.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bairro")
public class Bairro extends AbstractEntity<Integer> {

    private String nome;
    private String avenida;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "distrito_id", referencedColumnName = "id"),
            @JoinColumn(name = "provincia_id", referencedColumnName = "provincia_id")
    })
    private Distrito distrito;
}
