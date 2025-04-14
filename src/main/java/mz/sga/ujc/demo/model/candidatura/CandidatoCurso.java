package mz.sga.ujc.demo.model.candidatura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.model.restricoes.CandidatoCursoPk;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidato_curso")
public class CandidatoCurso {
    
    @EmbeddedId
    private CandidatoCursoPk id;

    @ManyToOne
    @MapsId("candidatoId")
    @JoinColumn(name = "candidato_id", referencedColumnName = "codigo")
    private Candidato candidato;

    @ManyToOne
    @MapsId("cursoId")
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @MapsId("provinciaId")
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @Column(nullable = false)
    private String periodo;

    @CreationTimestamp
    @Column(name = "data_registo", columnDefinition = "datetime", updatable = false)
    private Date data_registo;

    @Override
    public String toString() {
        return "CandidatoCurso{}";
    }
}
