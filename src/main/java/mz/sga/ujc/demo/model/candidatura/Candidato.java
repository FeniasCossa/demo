package mz.sga.ujc.demo.model.candidatura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.model.exame.Juri;
import mz.sga.ujc.demo.model.parametrization.Distrito;
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
                    @JoinColumn(name = "provincia_id", referencedColumnName = "provincia_id")}
    )
    private Distrito distrito;


    @OneToOne(mappedBy = "candidato")
    private Pagamento pagamento;

    @Column(name = "nome_pai")
    private String nomePai;

    @Column(name = "nome_mae")
    private String nomeMae;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "datetime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "datetime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "juri_id") // Essa será a FK em Candidato
    private Juri juri;



    @Override
    public String toString() {
        return "Candidato{}";
    }
}
