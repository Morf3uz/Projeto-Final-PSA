package br.edu.ifpr.bsi.ProjetoProgSofApp.service;

import br.edu.ifpr.bsi.ProjetoProgSofApp.mappers.DestinoMapper;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Destino;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    @Autowired
    private DestinoMapper destinoMapper;

    @Transactional
    public DestinoDetailDTO salvar(DestinoRequestDTO destinoRequestDTO) {
        Destino destino = this.destinoMapper.requestDTOToEntity(destinoRequestDTO);
        return this.destinoMapper.entityToDetailDTO(this.destinoRepository.save(destino));
    }

    public List<DestinoDetailDTO> listar() {
        return this.destinoRepository.findAll()
                .stream()
                .map(this.destinoMapper::entityToDetailDTO)
                .toList();
    }

    public DestinoDetailDTO obterPorId(Long id) {
        Destino destino = this.destinoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Destino não encontrado"));
        return this.destinoMapper.entityToDetailDTO(destino);
    }

    public List<DestinoDetailDTO> listarPorCategoria(String categoria) {
        return this.destinoRepository.findByCategoria(categoria)
                .stream()
                .map(this.destinoMapper::entityToDetailDTO)
                .toList();
    }

    @Transactional
    public DestinoDetailDTO atualizar(Long id, DestinoRequestDTO destinoRequestDTO) {
        this.destinoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Destino não encontrado"));
        Destino destino = this.destinoMapper.requestDTOToEntity(destinoRequestDTO);
        destino.setId(id);
        return this.destinoMapper.entityToDetailDTO(this.destinoRepository.save(destino));
    }

    @Transactional
    public void excluir(Long id) {
        this.destinoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Destino não encontrado"));
        this.destinoRepository.deleteById(id);
    }
}