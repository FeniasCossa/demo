package mz.sga.ujc.demo.service.candidatuta;


import mz.sga.ujc.demo.model.admin.DistribuicaoStatus;
import mz.sga.ujc.demo.model.candidatura.*;
import mz.sga.ujc.demo.model.exame.Instituicao;
import mz.sga.ujc.demo.model.exame.Juri;
import mz.sga.ujc.demo.model.exame.RealizacaoExame;
import mz.sga.ujc.demo.model.exame.Sala;
import mz.sga.ujc.demo.model.parametrization.Provincia;
import mz.sga.ujc.demo.model.restricoes.RealizacaoExamePk;
import mz.sga.ujc.demo.repository.candidatura.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class JuriService implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(JuriService.class);
    private final CandidatoRepository candidatoRepo;
    private final CandidatoCursoRepository candidatoCursoRepo;
    private final JuriRepository juriRepo;
    private final RealizacaoExameRepository realizacaoExameRepo;
    private final InstituicaoRepository instituicaoRepo;
    private final SalaRepository salaRepo;
    private final ExameRepository exameRepo;
    private final DisciplinaCursoRepository disciplinaCursoRepo;
    public static Map<String, DistribuicaoStatus> progressoMap = new ConcurrentHashMap<>();


    @Autowired
    public JuriService(CandidatoRepository candidatoRepository, CandidatoCursoRepository candidatoCursoRepo, JuriRepository juriRepository, RealizacaoExameRepository realizacaoExameRepo, InstituicaoRepository instituicaoRepo, SalaRepository salaRepo, ExameRepository exameRepo, DisciplinaCursoRepository disciplinaCursoRepo) {
        this.candidatoRepo = candidatoRepository;
        this.candidatoCursoRepo = candidatoCursoRepo;
        this.juriRepo = juriRepository;
        this.realizacaoExameRepo = realizacaoExameRepo;
        this.instituicaoRepo = instituicaoRepo;
        this.salaRepo = salaRepo;
        this.exameRepo = exameRepo;
        this.disciplinaCursoRepo = disciplinaCursoRepo;
    }

    @Transactional
    public void gerarJuris() {
        logger.info("Buscar todos os CandidatoCurso que ainda não têm Juri atribuído");
        List<CandidatoCurso> semJuri = candidatoCursoRepo.findCandidatoCursoByCandidatoJuriIsNull();
        DistribuicaoStatus status = new DistribuicaoStatus(semJuri.size());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 20);
        // Aqui está a data futura com data e hora
        Date dataFutura = calendar.getTime();
        LocalDateTime dataHoraInicio = dataFutura.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .withHour(9)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        progressoMap.put("distribuicao", status);

        if (semJuri.isEmpty()) {
            status.setMensagem("Distribuição concluída!");
            status.setConcluido(true);
            throw new RuntimeException("Não Candidatos Validos para atribuir Juris");
        }

        logger.info("Total de candidatos sem juri: {}", semJuri.size());

        logger.info("Criando a distribuição do progresso");

        logger.info("Agrupar os candidatos por curso e província");
        Map<String, List<CandidatoCurso>> agrupado = semJuri.stream()
                .collect(Collectors.groupingBy(cc ->
                        cc.getCurso().getId() + "_" + cc.getProvincia().getId()
                ));

        logger.info("Total de grupos para processar: {}", agrupado.size());

        logger.info("Processar cada grupo e gerar os Juri");
        for (var grupo : agrupado.entrySet()) {
            List<CandidatoCurso> lista = grupo.getValue();
            int total = lista.size();
            int juriNumero = 1;

            logger.info("Para cada grupo de 30 candidatos, gerar um Juri");
            for (int i = 0; i < total; i += 1) {
                logger.info("Criado Sublista dos candidatos {}", i);
                List<CandidatoCurso> subLista = lista.subList(i, Math.min(i + 1, total));

                Curso curso = subLista.get(0).getCurso();
                Provincia provincia = subLista.get(0).getProvincia();

                logger.info("Criar Juri {}", i);
                Juri juri = new Juri();
                juri.setNumero(String.valueOf(juriNumero++));
                juri.setCurso(curso);
                juri.setProvincia(provincia);
                juri = juriRepo.save(juri);

                logger.info("Atribuir os candidatos ao Juri");
                for (CandidatoCurso cc : subLista) {
                    Candidato candidato = cc.getCandidato();
                    candidato.setJuri(juri);

                    candidatoRepo.saveAll(subLista.stream().map(CandidatoCurso::getCandidato).collect(Collectors.toList()));

                    logger.info("Criar RealizacaoExame (associando o juri com uma instituição e sala)");
                    Instituicao instituicao = buscarInstituicaoDisponivel(provincia);

                    logger.info("Buscar sala disponível para o exame");
                    Sala sala = buscarSalaDisponivel(instituicao);

                    logger.info("Alocndor sala e gerar a realização de exame");
                    List<DisciplinaCurso> disciplinaCurso = disciplinaCursoRepo.getDisciplinaCursoByIdCurso(curso);
                    for (DisciplinaCurso value : disciplinaCurso) {
                        logger.info("Alocar sala e gerar a realização de exame para a disciplina de : {}", value.getId().getDisciplina().getNome());
                        Disciplina disciplina = value.getId().getDisciplina();
                        RealizacaoExame realizacaoExame = new RealizacaoExame();
                        RealizacaoExamePk pk = new RealizacaoExamePk();
                        pk.setCandidato(candidato.getCodigo());
                        pk.setInstituicao(instituicao.getId());
                        pk.setExame(exameRepo.getExameByDisciplina(disciplina).getId());
                        realizacaoExame.setId(pk);
                        realizacaoExame.setInstituicao(instituicao);
                        realizacaoExame.setJuri(juri);
                        realizacaoExame.setSala(sala);
                        realizacaoExame.setData(dataHoraInicio);
                        realizacaoExame.setDataHoraInicio(dataHoraInicio);
                        // Exemplo de data de exame
                        realizacaoExameRepo.save(realizacaoExame);
                    }
                }
                status.setProcessados(status.getProcessados() + Math.min(1, lista.size() - i));
                status.setMensagem("Distribuindo... " + status.getProcessados() + " de " + status.getTotal());
            }
        }
        status.setMensagem("Distribuição concluída!");
        status.setConcluido(true);
    }

    // Método para buscar a instituição disponível na província
    private Instituicao buscarInstituicaoDisponivel(Provincia provincia) {
        logger.info("Busca as instituições da província");
        List<Instituicao> instituicoes = instituicaoRepo.findByProvincia(provincia);

        for (Instituicao instituicao : instituicoes) {
            logger.info("Se a instituição tiver capacidade suficiente");
            if (temCapacidadeParaExame(instituicao)) {
                return instituicao;
            }
        }
        // Se nenhuma instituição tiver capacidade, lança uma exceção
        throw new RuntimeException("Não há instituições disponíveis com capacidade para os exames nesta província.");
    }

    // Método para verificar se a instituição tem capacidade para exames
    private boolean temCapacidadeParaExame(Instituicao instituicao) {
        logger.info("Verifica se a instituição tem ao menos uma sala disponível");
        List<Sala> salas = salaRepo.findByInstituicao(instituicao);
        return salas.stream().anyMatch(sala -> sala.getCapacidade() > 0);
    }

    // Método para buscar uma sala disponível
    private Sala buscarSalaDisponivel(Instituicao instituicao) {
        logger.info("Verifica as salas da instituição, ordena por capacidade e retorna a sala disponível");
        List<Sala> salas = salaRepo.findByInstituicao(instituicao);

        logger.info("Encontra a primeira sala disponível com capacidade suficiente");
        for (Sala sala : salas) {
            if (sala.getCapacidade() > 0) {
                logger.info("Atualiza a capacidade disponível");
                sala.setCapacidade(sala.getCapacidade() - 1);
                salaRepo.save(sala);
                return sala;
            }
        }
        throw new RuntimeException("Não há salas disponíveis para esta instituição.");
    }

    @Override
    public void run() {
        gerarJuris();
    }
}
