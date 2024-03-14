package mz.sga.ujc.demo.model.auth;

import lombok.*;
import mz.sga.ujc.demo.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString@Entity@Table(name = "perfil")
public class Perfil extends AbstractEntity<Integer> {

    private String nome;
    private String descricao;

}
