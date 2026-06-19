package br.edu.ifpr.bsi.ProjetoProgSofApp.repositories;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.DestinoService;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.PacoteDeTurismoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@SpringBootTest
@Transactional
public class PacoteDeTurismoRepositoryTest {

    @Autowired
    private PacoteDeTurismoService pacoteService;

    @Autowired
    private DestinoService destinoService;

    private DestinoDetailDTO criarDestino(String nome, String pais) {
        return destinoService.salvar(new DestinoRequestDTO(nome, "Descrição", 5, "Turismo", pais));
    }

    @Test
    public void testeSalvarPacote() {
        DestinoDetailDTO destino = criarDestino("Paris", "França");

        PacoteDeTurismoRequestDTO request = new PacoteDeTurismoRequestDTO("Pacote Paris", 5000.00, destino.id());
        PacoteDeTurismoDetailDTO salvo = pacoteService.salvar(request);

        Assertions.assertNotNull(salvo.id());
        Assertions.assertEquals("Pacote Paris", salvo.nome());
        Assertions.assertEquals("Paris", salvo.destino().nome());
    }

    @Test
    public void testeBuscarPorPrecoMaximo() {
        DestinoDetailDTO destino1 = criarDestino("Buenos Aires", "Argentina");
        pacoteService.salvar(new PacoteDeTurismoRequestDTO("Pacote Buenos Aires", 1500.00, destino1.id()));

        DestinoDetailDTO destino2 = criarDestino("Tokyo", "Japão");
        pacoteService.salvar(new PacoteDeTurismoRequestDTO("Pacote Tokyo", 8000.00, destino2.id()));

        List<PacoteDeTurismoDetailDTO> baratos = pacoteService.buscarPreco("2000");

        Assertions.assertFalse(baratos.isEmpty());
        Assertions.assertTrue(baratos.stream().anyMatch(p -> p.nome().equals("Pacote Buenos Aires")));
    }

    @Test
    public void testeAtualizarPacote() {
        DestinoDetailDTO destino = criarDestino("Lisboa", "Portugal");
        PacoteDeTurismoDetailDTO salvo = pacoteService.salvar(
                new PacoteDeTurismoRequestDTO("Pacote Original", 3000.00, destino.id()));

        PacoteDeTurismoDetailDTO atualizado = pacoteService.atualizar(salvo.id(),
                new PacoteDeTurismoRequestDTO("Pacote Original", 2500.00, destino.id()));

        Assertions.assertEquals(2500.00, atualizado.preco());
    }

    @Test
    public void testeDeletarPacote() {
        DestinoDetailDTO destino = criarDestino("Cancun", "México");
        PacoteDeTurismoDetailDTO salvo = pacoteService.salvar(
                new PacoteDeTurismoRequestDTO("Pacote Cancelado", 100.00, destino.id()));

        pacoteService.excluir(salvo.id());

        Assertions.assertThrows(ResponseStatusException.class, () -> pacoteService.obterPorId(salvo.id()));
    }
}