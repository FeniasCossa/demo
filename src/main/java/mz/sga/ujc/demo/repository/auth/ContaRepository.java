package mz.sga.ujc.demo.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mz.sga.ujc.demo.model.auth.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {

    Conta getReferenceByNuit(String nuit);

    @Query("select max(codigo) from Conta")
    int getCodigo();

    Conta getContaByTelefone(Integer telefone);

    Conta getReferenceByCodigo(Integer codigo);
}
