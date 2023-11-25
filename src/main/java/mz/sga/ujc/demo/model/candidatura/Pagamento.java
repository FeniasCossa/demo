package mz.sga.ujc.demo.model.candidatura;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.restricoes.PagamentoPK;

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
    @Column(name="data_pagamento")
    private Date data_pagamento;

    
   

}
