package mz.sga.ujc.demo.controller.admin;

import mz.sga.ujc.demo.model.admin.DistribuicaoStatus;
import mz.sga.ujc.demo.service.candidatuta.JuriService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progresso")
public class ProgressoController {

    @GetMapping
    public ResponseEntity<DistribuicaoStatus> getProgresso() {
        DistribuicaoStatus status = JuriService.progressoMap.get("distribuicao");
        if (status == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(status);
    }
}
