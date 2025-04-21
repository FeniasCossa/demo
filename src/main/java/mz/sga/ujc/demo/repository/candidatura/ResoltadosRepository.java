package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.exame.Resultado;
import mz.sga.ujc.demo.model.restricoes.ResultadoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResoltadosRepository extends JpaRepository<Resultado, ResultadoPk> {
}
