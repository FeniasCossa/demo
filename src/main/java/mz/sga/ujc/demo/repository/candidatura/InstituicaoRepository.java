package mz.sga.ujc.demo.repository.candidatura;

import mz.sga.ujc.demo.model.exame.Instituicao;
import mz.sga.ujc.demo.model.parametrization.Provincia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {
    List<Instituicao> findByProvincia(Provincia provincia);

    Page<Instituicao> findAll(Pageable pageable);
}
