package mz.sga.ujc.demo.model.candidatura;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaxaTotalCurso {
    private int id;
    private String valor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxaTotalCurso)) return false;
        TaxaTotalCurso that = (TaxaTotalCurso) o;
        return id == that.id && Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor);
    }
}
