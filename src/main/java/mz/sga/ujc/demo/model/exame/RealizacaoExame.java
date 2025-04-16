package mz.sga.ujc.demo.model.exame;

import lombok.*;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.restricoes.RealizacaoExamePk;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "realizacao_exame")
public class RealizacaoExame {
    
    @EmbeddedId
    private RealizacaoExamePk id;

    @ManyToOne
    @MapsId("candidatoId")  // Mapeia a chave candidata no EmbeddedId
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @ManyToOne
    @MapsId("instituicaoId")
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @ManyToOne
    @MapsId("exameId")
    @JoinColumn(name = "exame_id")
    private Exame exame;

    @ManyToOne
    @JoinColumn(name = "juri_id")
    private Juri juri;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;


    @Column(name = "data_realizacao", columnDefinition = "datetime")
    private LocalDateTime data;


    @Column(name = "hora_inicio", columnDefinition = "datetime")
    private LocalDateTime dataHoraInicio;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "datetime")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "datetime")
    private Date updatedAt;
}
