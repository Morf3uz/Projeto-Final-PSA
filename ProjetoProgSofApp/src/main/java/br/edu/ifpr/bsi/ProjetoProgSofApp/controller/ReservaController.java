package br.edu.ifpr.bsi.ProjetoProgSofApp.controller;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva.ReservaDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva.ReservaRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.ReservaService;
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
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDetailDTO> criar(@RequestBody ReservaRequestDTO reservaRequestDTO) {
        ReservaDetailDTO reservaSalva = this.reservaService.salvar(reservaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalva);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDetailDTO>> listar() {
        List<ReservaDetailDTO> reservas = this.reservaService.listar();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDetailDTO> obterPorId(@PathVariable Long id) {
        ReservaDetailDTO reserva = this.reservaService.obterPorId(id);
        return ResponseEntity.ok(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDetailDTO> atualizar(@PathVariable Long id,
                                                      @RequestBody ReservaRequestDTO reservaRequestDTO) {
        ReservaDetailDTO reservaAtualizada = this.reservaService.atualizar(id, reservaRequestDTO);
        return ResponseEntity.ok(reservaAtualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        this.reservaService.excluir(id);
    }
}