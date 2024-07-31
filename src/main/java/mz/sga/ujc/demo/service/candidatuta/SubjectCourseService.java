package mz.sga.ujc.demo.service.candidatuta;

import mz.sga.ujc.demo.model.candidatura.*;
import mz.sga.ujc.demo.repository.candidatura.DisciplinaCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubjectCourseService {

    private final DisciplinaCursoRepository disciplinaCursoRepository;
    List<TaxaTotalCurso> listTaxaByCursos;
    List<DisciplinaCurso> listaDisciplinaCurso;

    @Autowired
    public SubjectCourseService(DisciplinaCursoRepository disciplinaCursoRepository) {
        this.disciplinaCursoRepository = disciplinaCursoRepository;
        this.listTaxaByCursos = new ArrayList<>();
    }

    public List<TaxaTotalCurso> calcTotal() {
        listaDisciplinaCurso = disciplinaCursoRepository.findAll();
        double acum = 0;
        for (DisciplinaCurso curso : listaDisciplinaCurso) {
            TaxaTotalCurso ttc = new TaxaTotalCurso();
            StringBuilder dis= new StringBuilder();
            for (DisciplinaCurso disciplinaCurso : listaDisciplinaCurso) {
                if (Objects.equals(disciplinaCurso.getId().getCurso().getId(), curso.getId().getCurso().getId())) {
                    acum += disciplinaCurso.getId().getDisciplina().getTaxa().getValor();
                    dis.append(disciplinaCurso.getId().getDisciplina().getNome()).append(" ");
                }
                ttc.setId(curso.getId().getCurso().getId());
                ttc.setCurso(curso.getId().getCurso().getNome());
                ttc.setDisciplinas(dis.toString().replaceFirst(" ", " e "));
                ttc.setValor("" + acum);
            }
            listTaxaByCursos.add(ttc);
            acum = 0;
        }
        return listTaxaByCursos;
    }

    public Set<TaxaTotalCurso> DistinctTotalByCource() {
        return new HashSet<>(calcTotal());
    }

    public double getValor(Curso curso) {
        double valor = 0;
        for (int i = 0; i < calcTotal().size(); i++) {
            if (calcTotal().get(i).getId() == curso.getId()) {
                valor += Double.parseDouble(calcTotal().get(i).getValor());
                break;
            }
        }
        return valor;
    }

    public Factura getFactura(Pagamento pagamento) {
        StringBuilder disciplina = new StringBuilder();
        Factura factura = new Factura();
        factura.setNome(pagamento.getId().getCandidato().getNome()+ " "+ pagamento.getId().getCandidato().getApelido());
        factura.setCodigo(pagamento.getId().getCandidato().getCodigo());
        factura.setSexo(pagamento.getId().getCandidato().getGenero());
        factura.setCurso(pagamento.getId().getCurso().getNome());
        factura.setUniversidade("UJC");
        factura.setValor(pagamento.getValor());
        factura.setEntidade("1000918249");
        factura.setReferencia("897167665455566");
        factura.setEstado(pagamento.getEstado());
        factura.setData_registo(pagamento.getData_pagamento());
        listaDisciplinaCurso = disciplinaCursoRepository.findAll();
        for (DisciplinaCurso disciplinaCurso : listaDisciplinaCurso) {
            if (Objects.equals(disciplinaCurso.getId().getCurso().getId(), pagamento.getId().getCurso().getId())) {
                disciplina.append(disciplinaCurso.getId().getDisciplina().getNome()).append(" ");
            }
        }
        disciplina.deleteCharAt(disciplina.lastIndexOf(" "));
        factura.setDisciplinas(disciplina.toString().replace(" ", " e "));
        return factura;
    }
}
