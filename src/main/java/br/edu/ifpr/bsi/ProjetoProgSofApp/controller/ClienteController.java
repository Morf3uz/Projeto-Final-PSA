package br.edu.ifpr.bsi.ProjetoProgSofApp.controller;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDetailDTO> criar(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteDetailDTO clienteSalvo = this.clienteService.salvar(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDetailDTO>> listar() {
        List<ClienteDetailDTO> clientes = this.clienteService.listar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDetailDTO> obterPorId(@PathVariable Long id) {
        ClienteDetailDTO cliente = this.clienteService.obterPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDetailDTO> buscarPorCpf(@PathVariable String cpf) {
        return this.clienteService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDetailDTO> atualizar(@PathVariable Long id,
                                                      @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteDetailDTO clienteAtualizado = this.clienteService.atualizar(id, clienteRequestDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        this.clienteService.excluir(id);
    }
}