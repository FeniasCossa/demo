package mz.sga.ujc.demo.model.exame;

import lombok.*;
import mz.sga.ujc.demo.model.restricoes.RealizacaoExamePk;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

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


    @DateTimeFormat(iso=ISO.DATE)
    @Column(name = "datat_realizacao", columnDefinition = "DATE")
    private Date data;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "datetime")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "datetime")
    private Date updatedAt;
}
