package br.edu.ifpr.bsi.ProjetoProgSofApp.repositories;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma.CronogramaDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma.CronogramaRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.CronogramaService;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.DestinoService;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.PacoteDeTurismoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@Transactional
public class CronogramaRepositoryTest {

    @Autowired
    private CronogramaService cronogramaService;

    @Autowired
    private PacoteDeTurismoService pacoteService;

    @Autowired
    private DestinoService destinoService;

    private PacoteDeTurismoDetailDTO criarPacote(String nome) {
        DestinoDetailDTO destino = destinoService.salvar(
                new DestinoRequestDTO("Destino Teste", "Desc", 5, "Ecoturismo", "Brasil"));
        return pacoteService.salvar(new PacoteDeTurismoRequestDTO(nome, 1000.00, destino.id()));
    }

    @Test
    public void testeSalvarCronograma() {
        PacoteDeTurismoDetailDTO pacote = criarPacote("Pacote Teste");

        CronogramaRequestDTO request = new CronogramaRequestDTO("Visita guiada ao museu", "09:00", pacote.id());
        CronogramaDetailDTO salvo = cronogramaService.salvar(request);

        Assertions.assertNotNull(salvo.id());
        Assertions.assertEquals("Visita guiada ao museu", salvo.descricao());
        Assertions.assertEquals(pacote.id(), salvo.pacote().id());
    }

    @Test
    public void testeListarCronogramas() {
        PacoteDeTurismoDetailDTO pacote = criarPacote("Pacote Listagem");
        cronogramaService.salvar(new CronogramaRequestDTO("Passeio de barco", "14:00", pacote.id()));

        Assertions.assertFalse(cronogramaService.listar().isEmpty());
    }

    @Test
    public void testeAtualizarCronograma() {
        PacoteDeTurismoDetailDTO pacote = criarPacote("Pacote Update");
        CronogramaDetailDTO salvo = cronogramaService.salvar(
                new CronogramaRequestDTO("Descrição Original", "08:00", pacote.id()));

        CronogramaDetailDTO atualizado = cronogramaService.atualizar(salvo.id(),
                new CronogramaRequestDTO("Descrição Atualizada", "08:00", pacote.id()));

        Assertions.assertEquals("Descrição Atualizada", atualizado.descricao());
    }

    @Test
    public void testeDeletarCronograma() {
        PacoteDeTurismoDetailDTO pacote = criarPacote("Pacote Delete");
        CronogramaDetailDTO salvo = cronogramaService.salvar(
                new CronogramaRequestDTO("Trilha ecológica", "10:00", pacote.id()));

        cronogramaService.excluir(salvo.id());

        Assertions.assertThrows(ResponseStatusException.class, () -> cronogramaService.obterPorId(salvo.id()));
    }
}