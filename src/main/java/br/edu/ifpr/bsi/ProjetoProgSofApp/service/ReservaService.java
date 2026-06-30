package br.edu.ifpr.bsi.ProjetoProgSofApp.service;

import br.edu.ifpr.bsi.ProjetoProgSofApp.mappers.ReservaMapper;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Cliente;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.PacoteDeTurismo;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Reserva;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva.ReservaDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva.ReservaRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.ClienteRepository;
import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.PacoteDeTurismoRepository;
import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PacoteDeTurismoRepository pacoteRepository;

    @Autowired
    private ReservaMapper reservaMapper;

    @Transactional
    public ReservaDetailDTO salvar(ReservaRequestDTO reservaRequestDTO) {
        Reserva reserva = this.reservaMapper.requestDTOToEntity(reservaRequestDTO);
        reserva.setCliente(this.resolverCliente(reservaRequestDTO.clienteId()));
        reserva.setPacote(this.resolverPacote(reservaRequestDTO.pacoteId()));
        return this.reservaMapper.entityToDetailDTO(this.reservaRepository.save(reserva));
    }

    public List<ReservaDetailDTO> listar() {
        return this.reservaRepository.findAll()
                .stream()
                .map(this.reservaMapper::entityToDetailDTO)
                .toList();
    }

    public ReservaDetailDTO obterPorId(Long id) {
        Reserva reserva = this.reservaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));
        return this.reservaMapper.entityToDetailDTO(reserva);
    }

    @Transactional
    public ReservaDetailDTO atualizar(Long id, ReservaRequestDTO reservaRequestDTO) {
        this.reservaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));
        Reserva reserva = this.reservaMapper.requestDTOToEntity(reservaRequestDTO);
        reserva.setId(id);
        reserva.setCliente(this.resolverCliente(reservaRequestDTO.clienteId()));
        reserva.setPacote(this.resolverPacote(reservaRequestDTO.pacoteId()));
        return this.reservaMapper.entityToDetailDTO(this.reservaRepository.save(reserva));
    }

    @Transactional
    public void excluir(Long id) {
        this.reservaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));
        this.reservaRepository.deleteById(id);
    }

    private Cliente resolverCliente(Long clienteId) {
        if (clienteId == null) return null;
        return this.clienteRepository.findById(clienteId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com id: " + clienteId));
    }

    private PacoteDeTurismo resolverPacote(Long pacoteId) {
        if (pacoteId == null) return null;
        return this.pacoteRepository.findById(pacoteId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacote não encontrado com id: " + pacoteId));
    }
}