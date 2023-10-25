package mz.sga.ujc.demo.model.auth;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
    
    public Perfil(String nome, String descricao) {
        this.nome=nome;
        this.descricao=descricao;
    }

    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "perfil")
    private List<Conta> contas;

}
