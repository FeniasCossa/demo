package mz.sga.ujc.demo.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.sga.ujc.demo.model.auth.Perfil;

public interface PerfilRepository  extends JpaRepository<Perfil, Integer>{
    
}
