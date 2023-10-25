package mz.sga.ujc.demo.model.candidatura;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.model.enums.EstadoCivil;
import mz.sga.ujc.demo.model.enums.Genero;
import mz.sga.ujc.demo.model.parametrization.Distrito;
import mz.sga.ujc.demo.model.parametrization.Escola;
import mz.sga.ujc.demo.model.parametrization.Provincia;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="candidato")
public class Candidato {
    
    @Id
    private int id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name="escola_id")
    private Escola escola;

    @Column(name = "primeiro_nome")
    private String nome;

    @Column(name="ultimo_nome")
    private String apelido;

    @Column(name = "natural_provincia")
    private String natural_provincia;

    @Column(name="natural_distrito")
    private String natural_distrito;

    @Column(name="data_nascimento")
    private Date dataNascimento;

    @Column(name="estado_civil")
    private EstadoCivil estadoCivil;

    @Column(name="genero")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name="reside_distrito_id")
    private Distrito reside_distrito;

    @ManyToOne
    @JoinColumn(name = "resid_provincia_id")
    private Provincia reside_provincia;

    @Column(name="telefone_principal")
    private int telefonePrincipal;

    @Column(name="telefone_alternativo")
    private int telefoneAlternativo;

    @Column(name="nome_pai")
    private String nomPai;

    @Column(name="nome_mae")
    private String nomMae;

    @OneToMany(mappedBy = "candidato")
    private List<Documento> documento;

    
}
