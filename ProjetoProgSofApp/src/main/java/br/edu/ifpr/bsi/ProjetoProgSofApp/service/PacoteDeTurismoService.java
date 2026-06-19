package br.edu.ifpr.bsi.ProjetoProgSofApp.service;

import br.edu.ifpr.bsi.ProjetoProgSofApp.mappers.PacoteDeTurismoMapper;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Destino;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.PacoteDeTurismo;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.DestinoRepository;
import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.PacoteDeTurismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collections;
import java.util.List;

@Service
public class PacoteDeTurismoService {

    @Autowired
    private PacoteDeTurismoRepository pacoteRepository;

    @Autowired
    private DestinoRepository destinoRepository;

    @Autowired
    private PacoteDeTurismoMapper pacoteMapper;

    @Transactional
    public PacoteDeTurismoDetailDTO salvar(PacoteDeTurismoRequestDTO pacoteRequestDTO) {
        PacoteDeTurismo pacote = this.pacoteMapper.requestDTOToEntity(pacoteRequestDTO);
        pacote.setDestino(this.resolverDestino(pacoteRequestDTO.destinoId()));
        return this.pacoteMapper.entityToDetailDTO(this.pacoteRepository.save(pacote));
    }

    public List<PacoteDeTurismoDetailDTO> listar() {
        return this.pacoteRepository.findAll()
                .stream()
                .map(this.pacoteMapper::entityToDetailDTO)
                .toList();
    }

    public PacoteDeTurismoDetailDTO obterPorId(Long id) {
        PacoteDeTurismo pacote = this.pacoteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacote não encontrado"));
        return this.pacoteMapper.entityToDetailDTO(pacote);
    }

    public List<PacoteDeTurismoDetailDTO> buscarPreco(String valor) {
        try {
            Double valorMaximo = Double.parseDouble(valor);
            return this.pacoteRepository.findByPrecoLessThanEqual(valorMaximo)
                    .stream()
                    .map(this.pacoteMapper::entityToDetailDTO)
                    .toList();
        } catch (NumberFormatException e) {
            return Collections.emptyList();
        }
    }

    @Transactional
    public PacoteDeTurismoDetailDTO atualizar(Long id, PacoteDeTurismoRequestDTO pacoteRequestDTO) {
        this.pacoteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacote não encontrado"));
        PacoteDeTurismo pacote = this.pacoteMapper.requestDTOToEntity(pacoteRequestDTO);
        pacote.setId(id);
        pacote.setDestino(this.resolverDestino(pacoteRequestDTO.destinoId()));
        return this.pacoteMapper.entityToDetailDTO(this.pacoteRepository.save(pacote));
    }

    @Transactional
    public void excluir(Long id) {
        this.pacoteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacote não encontrado"));
        this.pacoteRepository.deleteById(id);
    }

    private Destino resolverDestino(Long destinoId) {
        if (destinoId == null) return null;
        return this.destinoRepository.findById(destinoId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Destino não encontrado com id: " + destinoId));
    }
}