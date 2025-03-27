package mz.sga.ujc.demo.model.auth;

import lombok.*;
import mz.sga.ujc.demo.model.AbstractEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString@Entity@Table(name = "perfil")
public class Perfil extends AbstractEntity<Integer> {


    private String nome;
    private String descricao;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "datetime")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "datetime")
    private Date updatedAt;

    public Perfil(Integer id, String nome, String descricao, Date createdAt, Date updatedAt) {
        this.setId(id);
        this.nome = nome;
        this.descricao = descricao;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
