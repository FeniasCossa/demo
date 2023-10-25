package mz.sga.ujc.demo.model.candidatura;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.AbstractEntity;

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
}
