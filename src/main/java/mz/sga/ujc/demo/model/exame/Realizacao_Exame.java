package mz.sga.ujc.demo.model.exame;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.restricoes.Realizacao_ExamePk;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "realizacao_exame")
public class Realizacao_Exame {
    
    @EmbeddedId
    private Realizacao_ExamePk id;

    @Column(name = "local_realizacao")
    private String local;

    @DateTimeFormat(iso=ISO.DATE)
    @Column(name = "datat_realizacao", columnDefinition = "DATE")
    private Date data;
}
