package br.edu.ifpr.bsi.ProjetoProgSofApp.service;

import br.edu.ifpr.bsi.ProjetoProgSofApp.mappers.CronogramaMapper;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Cronograma;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.PacoteDeTurismo;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma.CronogramaDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma.CronogramaRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.CronogramaRepository;
import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.PacoteDeTurismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class CronogramaService {

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Autowired
    private PacoteDeTurismoRepository pacoteRepository;

    @Autowired
    private CronogramaMapper cronogramaMapper;

    @Transactional
    public CronogramaDetailDTO salvar(CronogramaRequestDTO cronogramaRequestDTO) {
        Cronograma cronograma = this.cronogramaMapper.requestDTOToEntity(cronogramaRequestDTO);
        cronograma.setPacote(this.resolverPacote(cronogramaRequestDTO.pacoteId()));
        return this.cronogramaMapper.entityToDetailDTO(this.cronogramaRepository.save(cronograma));
    }

    public List<CronogramaDetailDTO> listar() {
        return this.cronogramaRepository.findAll()
                .stream()
                .map(this.cronogramaMapper::entityToDetailDTO)
                .toList();
    }

    public CronogramaDetailDTO obterPorId(Long id) {
        Cronograma cronograma = this.cronogramaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cronograma não encontrado"));
        return this.cronogramaMapper.entityToDetailDTO(cronograma);
    }

    @Transactional
    public CronogramaDetailDTO atualizar(Long id, CronogramaRequestDTO cronogramaRequestDTO) {
        this.cronogramaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cronograma não encontrado"));
        Cronograma cronograma = this.cronogramaMapper.requestDTOToEntity(cronogramaRequestDTO);
        cronograma.setId(id);
        cronograma.setPacote(this.resolverPacote(cronogramaRequestDTO.pacoteId()));
        return this.cronogramaMapper.entityToDetailDTO(this.cronogramaRepository.save(cronograma));
    }

    @Transactional
    public void excluir(Long id) {
        this.cronogramaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cronograma não encontrado"));
        this.cronogramaRepository.deleteById(id);
    }

    private PacoteDeTurismo resolverPacote(Long pacoteId) {
        if (pacoteId == null) return null;
        return this.pacoteRepository.findById(pacoteId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacote não encontrado com id: " + pacoteId));
    }
}