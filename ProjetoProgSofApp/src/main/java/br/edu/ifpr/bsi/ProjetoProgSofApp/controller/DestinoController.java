package br.edu.ifpr.bsi.ProjetoProgSofApp.controller;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.DestinoService;
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
@RequestMapping("/api/destinos")
@CrossOrigin(origins = "*")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @PostMapping
    public ResponseEntity<DestinoDetailDTO> criar(@RequestBody DestinoRequestDTO destinoRequestDTO) {
        DestinoDetailDTO destinoSalvo = this.destinoService.salvar(destinoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(destinoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<DestinoDetailDTO>> listar() {
        List<DestinoDetailDTO> destinos = this.destinoService.listar();
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinoDetailDTO> obterPorId(@PathVariable Long id) {
        DestinoDetailDTO destino = this.destinoService.obterPorId(id);
        return ResponseEntity.ok(destino);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<DestinoDetailDTO>> listarPorCategoria(@PathVariable String categoria) {
        List<DestinoDetailDTO> destinos = this.destinoService.listarPorCategoria(categoria);
        return ResponseEntity.ok(destinos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinoDetailDTO> atualizar(@PathVariable Long id,
                                                      @RequestBody DestinoRequestDTO destinoRequestDTO) {
        DestinoDetailDTO destinoAtualizado = this.destinoService.atualizar(id, destinoRequestDTO);
        return ResponseEntity.ok(destinoAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        this.destinoService.excluir(id);
    }
}