package mz.sga.ujc.demo.repository.auth;

import mz.sga.ujc.demo.model.auth.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {

    Conta getReferenceByNuit(String nuit);

    Conta getReferenceByCodigo(Integer codigo);
}
