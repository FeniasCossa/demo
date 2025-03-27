package mz.sga.ujc.demo.model.exame;

import lombok.*;
import mz.sga.ujc.demo.model.restricoes.SalaPk;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "sala")
public class Sala {

    @EmbeddedId
    private SalaPk id;

    @Column(name = "nome")
    private String nome;
}
