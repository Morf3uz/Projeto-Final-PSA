package br.edu.ifpr.bsi.ProjetoProgSofApp.controller;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.PacoteDeTurismoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/pacotes")
public class PacoteDeTurismoController {

    @Autowired
    private PacoteDeTurismoService pacoteService;

    @PostMapping
    public ResponseEntity<PacoteDeTurismoDetailDTO> criar(@RequestBody PacoteDeTurismoRequestDTO pacoteRequestDTO) {
        PacoteDeTurismoDetailDTO pacoteSalvo = this.pacoteService.salvar(pacoteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacoteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<PacoteDeTurismoDetailDTO>> listar() {
        List<PacoteDeTurismoDetailDTO> pacotes = this.pacoteService.listar();
        return ResponseEntity.ok(pacotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacoteDeTurismoDetailDTO> obterPorId(@PathVariable Long id) {
        PacoteDeTurismoDetailDTO pacote = this.pacoteService.obterPorId(id);
        return ResponseEntity.ok(pacote);
    }

    @GetMapping("/busca-preco")
    public ResponseEntity<List<PacoteDeTurismoDetailDTO>> buscarPorPreco(@RequestParam String valor) {
        List<PacoteDeTurismoDetailDTO> pacotes = this.pacoteService.buscarPreco(valor);
        return ResponseEntity.ok(pacotes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacoteDeTurismoDetailDTO> atualizar(@PathVariable Long id,
                                                              @RequestBody PacoteDeTurismoRequestDTO pacoteRequestDTO) {
        PacoteDeTurismoDetailDTO pacoteAtualizado = this.pacoteService.atualizar(id, pacoteRequestDTO);
        return ResponseEntity.ok(pacoteAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        this.pacoteService.excluir(id);
    }
}