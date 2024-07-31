package mz.sga.ujc.demo.service.candidatuta;

import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.repository.candidatura.CandidatoRepository;
import mz.sga.ujc.demo.service.auth.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidatoRepository repository;
    private final AccountService accountService;
    @Autowired
    public CandidateService(CandidatoRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public void save(Candidato candidato, Integer code) {
        candidato.setCodigo(code);
        candidato.setConta(accountService.getAccountByCode(code));
        repository.save(candidato);
    }

    public Candidato getCandidateByCode(Integer codigo) {
        return repository.getReferenceById(codigo);
    }


}
