package br.edu.ifpr.bsi.ProjetoProgSofApp.controller;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma.CronogramaDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma.CronogramaRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.CronogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cronogramas")
@CrossOrigin(origins = "*")
public class CronogramaController {

    @Autowired
    private CronogramaService cronogramaService;

    @PostMapping
    public ResponseEntity<CronogramaDetailDTO> criar(@RequestBody CronogramaRequestDTO cronogramaRequestDTO) {
        CronogramaDetailDTO cronogramaSalvo = this.cronogramaService.salvar(cronogramaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cronogramaSalvo);
    }

    @GetMapping
    public ResponseEntity<List<CronogramaDetailDTO>> listar() {
        List<CronogramaDetailDTO> cronogramas = this.cronogramaService.listar();
        return ResponseEntity.ok(cronogramas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CronogramaDetailDTO> obterPorId(@PathVariable Long id) {
        CronogramaDetailDTO cronograma = this.cronogramaService.obterPorId(id);
        return ResponseEntity.ok(cronograma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CronogramaDetailDTO> atualizar(@PathVariable Long id,
                                                         @RequestBody CronogramaRequestDTO cronogramaRequestDTO) {
        CronogramaDetailDTO cronogramaAtualizado = this.cronogramaService.atualizar(id, cronogramaRequestDTO);
        return ResponseEntity.ok(cronogramaAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        this.cronogramaService.excluir(id);
    }
}