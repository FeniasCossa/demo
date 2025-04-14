package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.exame.Juri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuriRepository extends JpaRepository<Juri, Long> {
}