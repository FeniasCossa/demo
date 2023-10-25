package mz.sga.ujc.demo.model.candidatura;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name="data_pagamento", columnDefinition="DATE")
    private Date data_pagamento;
}
