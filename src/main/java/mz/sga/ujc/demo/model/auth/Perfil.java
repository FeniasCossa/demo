package mz.sga.ujc.demo.model.auth;

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
@Table(name = "perfil")
public class Perfil extends AbstractEntity<Integer>{
    
    private String nome;
    private String descricao;

}
