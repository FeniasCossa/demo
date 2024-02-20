package mz.sga.ujc.demo.model.exame;

import java.time.LocalTime;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.AbstractEntity;
import mz.sga.ujc.demo.model.candidatura.Disciplina;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "exame")
public class Exame extends AbstractEntity<Integer>{

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @DateTimeFormat(iso = ISO.TIME)
    @Column(name = "duracao", columnDefinition = "TIME")
    private LocalTime duracao;
}
