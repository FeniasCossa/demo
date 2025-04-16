package mz.sga.ujc.demo.model.candidatura;

import lombok.*;
import mz.sga.ujc.demo.model.restricoes.PagamentoPK;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pagamento")
public class Pagamento {
    
    @EmbeddedId
    private PagamentoPK id;

    @ManyToOne
    @MapsId("cursoId")
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToOne
    @MapsId("candidatoId") // o nome deve bater com o campo da EmbeddedId
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @Column(updatable = false)
    private double valor;

    private String estado;

    @Column(name = "metodo_pagamento")
    private String metodoPagamento;

    @Column(name = "conta_numero")
    private String cardNumber;


    @CreationTimestamp
    @Column(name="created_at", columnDefinition = "datetime", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", columnDefinition = "datetime")
    private Date updated_at;

    @CreationTimestamp
    @Column(name="data_pagamento", columnDefinition = "datetime")
    private Date data_pagamento;


}
