package mz.sga.ujc.demo.service.candidatuta;

import mz.sga.ujc.demo.model.candidatura.*;
import mz.sga.ujc.demo.model.restricoes.DisciplinaCursoPk;
import mz.sga.ujc.demo.repository.candidatura.DisciplinaCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DisciplinaCursoService {
    private final DisciplinaCursoRepository disciplinaCursoRepository;
    List<TaxaTotalCurso> listTaxaByCursos= new ArrayList<>();
    List<DisciplinaCurso> listaDisciplinaCurso;

    @Autowired
    public DisciplinaCursoService(DisciplinaCursoRepository disciplinaCursoRepository) {
        this.disciplinaCursoRepository = disciplinaCursoRepository;
    }

    public List<TaxaTotalCurso> calcTotal(){
        listaDisciplinaCurso = disciplinaCursoRepository.findAll();
        double acum =0;
        Iterator<DisciplinaCurso> iterator= listaDisciplinaCurso.iterator();
        while (iterator.hasNext()) {
            TaxaTotalCurso ttc= new TaxaTotalCurso();
            DisciplinaCurso dc = iterator.next();
            for (int i = 0; i < listaDisciplinaCurso.size(); i++) {
                if (listaDisciplinaCurso.get(i).getId().getCurso().getId() == dc.getId().getCurso().getId()) {
                    acum += listaDisciplinaCurso.get(i).getId().getDisciplina().getTaxa().getValor();
                }
                ttc.setId(dc.getId().getCurso().getId());
                ttc.setValor("" + acum);
            }
            listTaxaByCursos.add(ttc);
            acum=0;
        }
        return listTaxaByCursos;
    }

    public Set<TaxaTotalCurso> DistinctTotalByCurso(){
        Set<TaxaTotalCurso> taxaTotalCursoSet =  new HashSet<>();
        taxaTotalCursoSet.addAll(calcTotal());
        return taxaTotalCursoSet;
    }
}
