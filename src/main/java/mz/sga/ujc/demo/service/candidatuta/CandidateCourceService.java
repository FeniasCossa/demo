package mz.sga.ujc.demo.service.candidatuta;

import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.repository.candidatura.CandidatoCursoRepository;
import mz.sga.ujc.demo.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateCourceService {

    private final SubjectCourseService subjectCourseService;
    private final CandidatoCursoRepository candidatoCursoRepository;
    private final PaymentService paymentService;
    @Autowired
    public CandidateCourceService(SubjectCourseService subjectCourseService, CandidatoCursoRepository candidatoCursoRepository, PaymentService paymentService) {
        this.subjectCourseService = subjectCourseService;
        this.candidatoCursoRepository = candidatoCursoRepository;
        this.paymentService = paymentService;
    }

    public void save(CandidatoCurso candidatoCurso){
        CandidatoCurso cc= candidatoCursoRepository.getCandidatoCursoByIdCandidatoId(candidatoCurso.getCandidato().getCodigo());
        if(cc!=null){
            candidatoCurso.setId(cc.getId());
            candidatoCursoRepository.save(candidatoCurso);
        }else{
            candidatoCursoRepository.save(candidatoCurso);
        }
    }
}
