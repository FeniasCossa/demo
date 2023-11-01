package mz.sga.ujc.demo.repository.parametrization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.sga.ujc.demo.model.parametrization.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer>{
    
}
