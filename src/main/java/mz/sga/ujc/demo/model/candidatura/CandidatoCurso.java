package mz.sga.ujc.demo.model.candidatura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.sga.ujc.demo.model.restricoes.Candidato_CursoPk;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidato_curso")
public class CandidatoCurso {
    
    @EmbeddedId
    private Candidato_CursoPk id;

    @Column(nullable = false)
    private String periodo;

    @CreationTimestamp
    @Column(name = "data_registo", columnDefinition = "datetime", updatable = false)
    private LocalDateTime data_registo;

    @Override
    public String toString() {
        return "CandidatoCurso{}";
    }
}
