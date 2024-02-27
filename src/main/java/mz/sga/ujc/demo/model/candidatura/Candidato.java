package mz.sga.ujc.demo.model.candidatura;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.model.parametrization.Distrito;
import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.model.restricoes.CandidatoPk;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "candidato")
public class Candidato{

    @Id
    private Integer codigo;

    @Column(name = "primeiro_nome")
    private String nome;

    @Column(name = "ultimo_nome")
    private String apelido;

    @Column(name = "genero")
    private String genero;

    @Column(name = "natural_provincia")
    private String naturalProvincia;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_nascimento", columnDefinition = "date")
    private Date dataNascimento;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @ManyToOne
    @JoinColumns(
            {@JoinColumn(name = "distrito_id", referencedColumnName = "id"),
            @JoinColumn(name="provincia_id", referencedColumnName = "provincia_id")}
    )
    private Distrito distrito;

    @Column(name = "nome_pai")
    private String nomePai;

    @Column(name = "nome_mae")
    private String nomeMae;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "datetime")
    private Date updatedAt;

    @OneToOne
    @JoinColumn(name = "conta_id", insertable = true)
    private Conta conta;
}
