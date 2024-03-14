package mz.sga.ujc.demo.model.candidatura;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.restricoes.Candidato_CursoPk;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "candidato_curso")
public class CandidatoCurso {
    
    @EmbeddedId
    private Candidato_CursoPk id;

    @Column(nullable = false)
    private String periodo;

    @CreationTimestamp
    @Column(name = "data_registo", columnDefinition = "datetime")
    private LocalDateTime data_registo;
}
