package mz.sga.ujc.demo.repository.auth;

import mz.sga.ujc.demo.model.auth.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {

    Conta getReferenceByNuit(String nuit);

    @Query("select max(codigo) from Conta")
    int getCodigo();

    Conta getContaByTelefone(Integer telefone);

    Conta getReferenceByCodigo(Integer codigo);

    @Query("select c from Conta c where c.codigo = :codigo and c.senha = :senha")
    public Conta buscarLogin(String codigo, String senha);
}
