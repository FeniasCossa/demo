package mz.sga.ujc.demo.model.admin;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DistribuicaoStatus {
    // Getters e setters
    private int total;
    private int processados;
    private boolean concluido;
    private String mensagem;

    public DistribuicaoStatus(int total) {
        this.total = total;
        this.processados = 0;
        this.concluido = false;
        this.mensagem = "Distribuição iniciada...";
    }

}

