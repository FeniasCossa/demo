package mz.sga.ujc.demo.model.candidatura;

import java.util.Date;

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
import mz.sga.ujc.demo.model.restricoes.Candidato_CursoPk;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "candidato_curso")
public class Candidato_Curso {
    
    @EmbeddedId
    private Candidato_CursoPk id;
    
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_registo", columnDefinition = "DATE")
    private Date data_registo;
}
