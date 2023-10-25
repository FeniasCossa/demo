package mz.sga.ujc.demo.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.sga.ujc.demo.model.auth.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    Conta getReferenceByNuit(Integer nuit);
    
}
