package mz.sga.ujc.demo.model.exame;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
@Table(name = "resultado")
public class Resultado {
    
    @EmbeddedId
    private Realizacao_ExamePk id;

    @Column(name = "nota")
    @Size(min = 0, max = 20, message = "A nota só pode estar no intervalo de {min} a {max}")
    private float nota;
}
