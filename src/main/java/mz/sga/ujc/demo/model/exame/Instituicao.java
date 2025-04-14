package mz.sga.ujc.demo.model.exame;

import lombok.*;
import mz.sga.ujc.demo.model.AbstractEntity;
import mz.sga.ujc.demo.model.parametrization.Provincia;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "instituicao")
public class Instituicao extends AbstractEntity<Integer> {

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @OneToMany(mappedBy = "instituicao")
    private List<Sala> salas;
}
