package mz.sga.ujc.demo.model.candidatura;

import lombok.*;
import mz.sga.ujc.demo.model.restricoes.PagamentoPK;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    private double valor;
    private String estado;

    @CreationTimestamp
    @Column(name="data_pagamento", columnDefinition = "datetime")
    private Date data_pagamento;


}
