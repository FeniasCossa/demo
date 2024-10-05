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
    @JoinColumn(name = "curso_id")
    private Curso curso;

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
    @Column(name="data_pagamento", columnDefinition = "datetime", insertable = false)
    private Date data_pagamento;


}
