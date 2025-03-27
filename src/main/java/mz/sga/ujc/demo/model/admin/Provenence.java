package mz.sga.ujc.demo.model.admin;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Provenence {
    private String nome;

    @NumberFormat(pattern = "#,###.00")
    private double valor;

}
