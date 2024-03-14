package mz.sga.ujc.demo.repository.parametrization;

import mz.sga.ujc.demo.model.parametrization.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {

}
