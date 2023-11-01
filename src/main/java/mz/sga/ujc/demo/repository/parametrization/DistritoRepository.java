package mz.sga.ujc.demo.repository.parametrization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.sga.ujc.demo.model.parametrization.Distrito;
import mz.sga.ujc.demo.model.restricoes.DistritoPK;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito,DistritoPK> {
    
}
