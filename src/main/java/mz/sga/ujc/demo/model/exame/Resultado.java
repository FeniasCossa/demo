package mz.sga.ujc.demo.model.exame;

import lombok.*;
import mz.sga.ujc.demo.model.restricoes.ResultadoPk;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "resultado")
public class Resultado {
    
    @EmbeddedId
    private ResultadoPk id;

    @Column(name = "nota")
    @Size(max = 20, message = "A nota só pode estar no intervalo de 0 a {max}")
    private float nota;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "datetime")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "datetime")
    private Date updatedAt;
}
