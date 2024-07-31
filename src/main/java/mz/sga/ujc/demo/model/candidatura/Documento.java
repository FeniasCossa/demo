package mz.sga.ujc.demo.model.candidatura;

import lombok.*;
import mz.sga.ujc.demo.model.AbstractEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "documento")
public class Documento extends AbstractEntity<Integer>{
    
    @ManyToOne
    @JoinColumn(name="candidato_id")
    private Candidato candidato;

    private String tipo;
    private String numero;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_validade", columnDefinition = "DATE")
    private Date validade;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "datetime")
    private java.sql.Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "datetime")
    private java.sql.Date updatedAt;
}
