package mz.sga.ujc.demo.model.candidatura;

import lombok.*;
import mz.sga.ujc.demo.model.AbstractEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "disciplina")
public class Disciplina extends AbstractEntity<Integer>{
    
    private String nome;

    @ManyToOne
    @JoinColumn(name = "taxa_id", nullable = false)
    private Taxa taxa;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "datetime")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "datetime")
    private Date updatedAt;
}
