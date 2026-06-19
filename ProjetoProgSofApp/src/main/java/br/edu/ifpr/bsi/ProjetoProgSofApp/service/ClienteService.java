package br.edu.ifpr.bsi.ProjetoProgSofApp.service;

import br.edu.ifpr.bsi.ProjetoProgSofApp.mappers.ClienteMapper;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Cliente;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ClienteDetailDTO salvar(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = this.clienteMapper.requestDTOToEntity(clienteRequestDTO);
        
        
        String cpfLimpo = clienteRequestDTO.cpf().replaceAll("[^0-9]", "");
        cliente.setCpf(cpfLimpo);
        
        cliente.setSenha(passwordEncoder.encode(clienteRequestDTO.senha()));
        return this.clienteMapper.entityToDetailDTO(this.clienteRepository.save(cliente));
    }

    public List<ClienteDetailDTO> listar() {
        return this.clienteRepository.findAll()
                .stream()
                .map(this.clienteMapper::entityToDetailDTO)
                .toList();
    }

    public ClienteDetailDTO obterPorId(Long id) {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return this.clienteMapper.entityToDetailDTO(cliente);
    }

    public Optional<ClienteDetailDTO> buscarPorCpf(String cpf) {
        return this.clienteRepository.findByCpf(cpf)
                .map(this.clienteMapper::entityToDetailDTO);
    }

    @Transactional
    public ClienteDetailDTO atualizar(Long id, ClienteRequestDTO clienteRequestDTO) {
        this.clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        
        Cliente cliente = this.clienteMapper.requestDTOToEntity(clienteRequestDTO);
        cliente.setId(id);
        
        
        String cpfLimpo = clienteRequestDTO.cpf().replaceAll("[^0-9]", "");
        cliente.setCpf(cpfLimpo);
        
        cliente.setSenha(passwordEncoder.encode(clienteRequestDTO.senha()));
        return this.clienteMapper.entityToDetailDTO(this.clienteRepository.save(cliente));
    }

    @Transactional
    public void excluir(Long id) {
        this.clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        this.clienteRepository.deleteById(id);
    }
}