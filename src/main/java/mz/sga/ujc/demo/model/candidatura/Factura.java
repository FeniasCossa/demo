package mz.sga.ujc.demo.model.candidatura;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Factura {
    private Integer codigo;
    private String nome;
    private String sexo;
    private String universidade;
    private String curso;
    private String disciplinas;
    private double valor;
    private String entidade;
    private String referencia;
    private String estado;
    private Date data_registo;
}
