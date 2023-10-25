package mz.sga.ujc.demo.model.candidatura;

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
import mz.sga.ujc.demo.model.restricoes.Disciplina_CursoPk;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "disciplina_curso")
public class Disciplina_Curso {
    
    @EmbeddedId
    private Disciplina_CursoPk id;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_criacao", columnDefinition = "DATE")
    private Date data_criacao;
    
    private Float peso_disciplina;

}
