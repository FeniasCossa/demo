package mz.sga.ujc.demo.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.sga.ujc.demo.model.auth.Permissoes;

public interface PermissoesRepository extends JpaRepository<Permissoes, Integer>{
    
}
