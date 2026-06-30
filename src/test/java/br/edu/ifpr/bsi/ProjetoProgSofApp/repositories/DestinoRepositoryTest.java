package br.edu.ifpr.bsi.ProjetoProgSofApp.repositories;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.DestinoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@SpringBootTest
@Transactional
public class DestinoRepositoryTest {

    @Autowired
    private DestinoService destinoService;

    @Test
    public void testeSalvarDestino() {
        DestinoRequestDTO request = new DestinoRequestDTO("Gramado", "Serra gaúcha", 5, "Montanha", "Brasil");

        DestinoDetailDTO salvo = destinoService.salvar(request);

        Assertions.assertNotNull(salvo.id());
        Assertions.assertEquals("Gramado", salvo.nome());
    }

    @Test
    public void testeBuscarPorCategoria() {
        DestinoRequestDTO request = new DestinoRequestDTO("Serra Gaúcha", "Montanhas do sul", 5, "Montanha", "Brasil");
        destinoService.salvar(request);

        List<DestinoDetailDTO> resultado = destinoService.listarPorCategoria("Montanha");

        Assertions.assertFalse(resultado.isEmpty());
        Assertions.assertEquals("Montanha", resultado.get(0).categoria());
    }

    @Test
    public void testeAtualizarDestino() {
        DestinoRequestDTO request = new DestinoRequestDTO("Nome Original", "Descricao", 3, "Histórico", "Brasil");
        DestinoDetailDTO salvo = destinoService.salvar(request);

        DestinoRequestDTO atualizado = new DestinoRequestDTO("Nome Atualizado", "Descricao", 3, "Histórico", "Brasil");
        DestinoDetailDTO resultado = destinoService.atualizar(salvo.id(), atualizado);

        Assertions.assertEquals("Nome Atualizado", resultado.nome());
    }

    @Test
    public void testeDeletarDestino() {
        DestinoRequestDTO request = new DestinoRequestDTO("Destino Remover", "Desc", 2, "Praia", "Brasil");
        DestinoDetailDTO salvo = destinoService.salvar(request);

        destinoService.excluir(salvo.id());

        Assertions.assertThrows(ResponseStatusException.class, () -> destinoService.obterPorId(salvo.id()));
    }
}