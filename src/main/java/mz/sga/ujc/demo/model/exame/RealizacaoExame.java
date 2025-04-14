package mz.sga.ujc.demo.model.exame;

import lombok.*;
import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.restricoes.RealizacaoExamePk;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

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
    @MapsId("candidato")  // Mapeia a chave candidata no EmbeddedId
    private Candidato candidato;

    @ManyToOne
    @MapsId("instituicao")
    private Instituicao instituicao;

    @ManyToOne
    @MapsId("exame")
    private Exame exame;

    @ManyToOne
    @JoinColumn(name = "juri_id")
    private Juri juri;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @DateTimeFormat(iso=ISO.DATE)
    @Column(name = "data_realizacao", columnDefinition = "DATE")
    private Date data;

    @DateTimeFormat(iso=ISO.DATE)
    @Column(name = "hora_inicio", columnDefinition = "DATE")
    private LocalDateTime dataHoraInicio;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "datetime")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "datetime")
    private Date updatedAt;
}
