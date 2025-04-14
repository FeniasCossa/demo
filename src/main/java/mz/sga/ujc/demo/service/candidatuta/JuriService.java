package mz.sga.ujc.demo.service.candidatuta;


import mz.sga.ujc.demo.model.candidatura.Candidato;
import mz.sga.ujc.demo.model.candidatura.CandidatoCurso;
import mz.sga.ujc.demo.model.candidatura.Curso;
import mz.sga.ujc.demo.model.exame.Instituicao;
import mz.sga.ujc.demo.model.exame.Juri;
import mz.sga.ujc.demo.model.exame.RealizacaoExame;
import mz.sga.ujc.demo.model.exame.Sala;
import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.repository.candidatura.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JuriService {

    private final CandidatoRepository candidatoRepo;
    private final CandidatoCursoRepository candidatoCursoRepo;
    private final JuriRepository juriRepo;
    private final RealizacaoExameRepository realizacaoExameRepo;
    private final InstituicaoRepository instituicaoRepo;
    private final SalaRepository salaRepo;

    @Autowired
    public JuriService(CandidatoRepository candidatoRepository, CandidatoCursoRepository candidatoCursoRepo, JuriRepository juriRepository, RealizacaoExameRepository realizacaoExameRepo, InstituicaoRepository instituicaoRepo, SalaRepository salaRepo) {
        this.candidatoRepo = candidatoRepository;
        this.candidatoCursoRepo= candidatoCursoRepo;
        this.juriRepo = juriRepository;
        this.realizacaoExameRepo = realizacaoExameRepo;
        this.instituicaoRepo = instituicaoRepo;
        this.salaRepo = salaRepo;
    }

    @Transactional
    public void gerarJuris() {
        // 1. Buscar todos os CandidatoCurso que ainda não têm Juri atribuído
        List<CandidatoCurso> semJuri = candidatoCursoRepo.findCandidatoCursoByCandidatoJuriIsNull();

        // 2. Agrupar os candidatos por curso e província
        Map<String, List<CandidatoCurso>> agrupado = semJuri.stream()
                .collect(Collectors.groupingBy(cc ->
                        cc.getCurso().getId() + "_" + cc.getProvincia().getId()
                ));

        // 3. Processar cada grupo e gerar os Juri
        for (var grupo : agrupado.entrySet()) {
            List<CandidatoCurso> lista = grupo.getValue();
            int total = lista.size();
            int juriNumero = 1;

            // 4. Para cada grupo de 30 candidatos, gerar um Juri
            for (int i = 0; i < total; i += 30) {
                List<CandidatoCurso> subLista = lista.subList(i, Math.min(i + 30, total));

                Curso curso = subLista.get(0).getCurso();
                Provincia provincia = subLista.get(0).getProvincia();

                // 5. Criar Juri
                Juri juri = new Juri();
                juri.setNumero(String.valueOf(juriNumero++));
                juri.setCurso(curso);
                juri.setProvincia(provincia);
                juri = juriRepo.save(juri);

                // 6. Atribuir os candidatos ao Juri
                for (CandidatoCurso cc : subLista) {
                    Candidato candidato = cc.getCandidato();
                    candidato.setJuri(juri);
                }
                candidatoRepo.saveAll(subLista.stream().map(CandidatoCurso::getCandidato).collect(Collectors.toList()));

                // 7. Criar RealizacaoExame (associando o juri com uma instituição e sala)
                Instituicao instituicao = buscarInstituicaoDisponivel(provincia);

                // 8. Buscar sala disponível para o exame
                Sala sala = buscarSalaDisponivel(instituicao);

                // 9. Alocar sala e gerar a realização de exame
                RealizacaoExame realizacaoExame = new RealizacaoExame();
                realizacaoExame.setInstituicao(instituicao);
                realizacaoExame.setJuri(juri);
                realizacaoExame.setSala(sala);
                realizacaoExame.setDataHoraInicio(LocalDateTime.now().plusDays(20)); // Exemplo de data de exame
                realizacaoExameRepo.save(realizacaoExame);
            }
        }
    }

    // Método para buscar a instituição disponível na província
    private Instituicao buscarInstituicaoDisponivel(Provincia provincia) {
        // Busca as instituições da província
        List<Instituicao> instituicoes = instituicaoRepo.findByProvincia(provincia);

        for (Instituicao instituicao : instituicoes) {
            // Se a instituição tiver capacidade suficiente
            if (temCapacidadeParaExame(instituicao)) {
                return instituicao;
            }
        }
        // Se nenhuma instituição tiver capacidade, lança uma exceção
        throw new RuntimeException("Não há instituições disponíveis com capacidade para os exames nesta província.");
    }

    // Método para verificar se a instituição tem capacidade para exames
    private boolean temCapacidadeParaExame(Instituicao instituicao) {
        // Verifica se a instituição tem ao menos uma sala disponível
        List<Sala> salas = salaRepo.findByInstituicao(instituicao);
        return salas.stream().anyMatch(sala -> sala.getCapacidade() > 0);
    }

    // Método para buscar uma sala disponível
    private Sala buscarSalaDisponivel(Instituicao instituicao) {
        // Verifica as salas da instituição, ordena por capacidade e retorna a sala disponível
        List<Sala> salas = salaRepo.findByInstituicao(instituicao);

        // Encontra a primeira sala disponível com capacidade suficiente
        for (Sala sala : salas) {
            if (sala.getCapacidade() > 0) {
                sala.setCapacidade(sala.getCapacidade() - 1); // Atualiza a capacidade disponível
                salaRepo.save(sala); // Atualiza a sala
                return sala; // Retorna a sala disponível
            }
        }
        throw new RuntimeException("Não há salas disponíveis para esta instituição.");
    }
}
