package mz.sga.ujc.demo.model.admin;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Provenance {
    private String nome;

    @NumberFormat(pattern = "#,###.00")
    private double valor;

    public Provenance(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    private Date date;
    private double totalPorDia;

    public Provenance(Date date, double totalPorDia) {
        this.date = date;
        this.totalPorDia = totalPorDia;
    }
}
