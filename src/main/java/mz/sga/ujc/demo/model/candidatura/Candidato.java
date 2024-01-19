package mz.sga.ujc.demo.model.candidatura;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class Candidato {

    @Id
    private int codigo;

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

    @Column(name = "distrito_id")
    private int distrito;

    @Column(name = "provincia_id")
    private int provincia;

    @Column(name = "telefone_principal")
    private int telefonePrincipal;

    @Column(name = "telefone_alternativo")
    private int telefoneAlternativo;

    @Column(name = "nome_pai")
    private String nomePai;

    @Column(name = "nome_mae")
    private String nomeMae;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
