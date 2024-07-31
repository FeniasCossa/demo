package mz.sga.ujc.demo.service.candidatuta;

import mz.sga.ujc.demo.model.candidatura.Documento;
import mz.sga.ujc.demo.model.parametrization.Escola;
import mz.sga.ujc.demo.repository.candidatura.DocumentoRepository;
import mz.sga.ujc.demo.repository.parametrization.EscolaRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolAndDocumentService {
    private final DocumentoRepository documentoRepository;
    private final EscolaRepostitory escolaRepostitory;
    @Autowired
    public SchoolAndDocumentService(DocumentoRepository documentoRepository, EscolaRepostitory escolaRepostitory) {
        this.documentoRepository = documentoRepository;
        this.escolaRepostitory = escolaRepostitory;
    }

    public void save(Documento documento, Escola escola){
        documentoRepository.save(documento);
        escolaRepostitory.save(escola);
    }
}
