package mz.sga.ujc.demo.repository.parametrization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.sga.ujc.demo.model.parametrization.Escola;

@Repository
public interface EscolaRepostitory extends JpaRepository<Escola, Integer>{
    
}
