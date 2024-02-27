package mz.sga.ujc.demo.model.candidatura;

import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.restricoes.DisciplinaCursoPk;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "disciplina_curso")
public class DisciplinaCurso {
    
    @EmbeddedId
    private DisciplinaCursoPk id;

    @Column(name = "peso_disciplina",nullable = false)
    private Float pesoDisciplina;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "datetime", nullable = false,insertable = true, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "datetime")
    private Date updatedAt;

    @Transient
    private double calc_column;

}
