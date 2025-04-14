package mz.sga.ujc.demo.service.candidatuta;

import mz.sga.ujc.demo.model.candidatura.*;
import mz.sga.ujc.demo.repository.candidatura.DisciplinaCursoRepository;
import mz.sga.ujc.demo.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubjectCourseService {

    private final DisciplinaCursoRepository disciplinaCursoRepository;
    private final List<TaxaTotalCurso> listTaxaByCursos;
    List<DisciplinaCurso> listaDisciplinaCurso;

    private final PaymentService paymentService;


    @Autowired
    public SubjectCourseService(DisciplinaCursoRepository disciplinaCursoRepository, PaymentService paymentService) {
        this.disciplinaCursoRepository = disciplinaCursoRepository;
        this.paymentService = paymentService;
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

    public Factura getFactura(CandidatoCurso candidatoCurso) {
        StringBuilder discipline = new StringBuilder();
        Factura factura = new Factura();
        Pagamento pagamento = paymentService.getPaymentByCandidate(candidatoCurso.getCandidato());
        factura.setNome(candidatoCurso.getCandidato().getNome()+ " "+ candidatoCurso.getCandidato().getApelido());
        factura.setCodigo(candidatoCurso.getCandidato().getCodigo());
        factura.setSexo(candidatoCurso.getCandidato().getGenero());
        factura.setCurso(candidatoCurso.getCurso().getNome());
        factura.setUniversidade("UJC");
        factura.setBank("BCI");
        if(pagamento==null){
            factura.setMetodo("");
            factura.setDataPagamento(null);
            factura.setValor(getValor(candidatoCurso.getCurso()));
            factura.setEstado("Não Pago");
            factura.setData_registo((Date) candidatoCurso.getData_registo());
        }else{
            factura.setMetodo(pagamento.getMetodoPagamento());
            factura.setDataPagamento(pagamento.getData_pagamento());
            factura.setValor(pagamento.getValor());
            factura.setEstado(pagamento.getEstado());
            factura.setData_registo(candidatoCurso.getData_registo());
        }

        factura.setEntidade("1000918249");
        factura.setReferencia("897167665455566");

        listaDisciplinaCurso = disciplinaCursoRepository.findAll();
        for (DisciplinaCurso disciplinaCurso : listaDisciplinaCurso)
            if (Objects.equals(disciplinaCurso.getId().getCurso().getId(), candidatoCurso.getCurso().getId()))
                discipline.append(disciplinaCurso.getId().getDisciplina().getNome()).append(", ");
        discipline.deleteCharAt(discipline.lastIndexOf(", "));
         factura.setDisciplinas(discipline.toString());
        return factura;
    }
}
