package br.edu.ifpr.bsi.ProjetoProgSofApp.repositories;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva.ReservaDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva.ReservaRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.ClienteService;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.DestinoService;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.PacoteDeTurismoService;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.ReservaService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@Transactional
public class ReservaRepositoryTest {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PacoteDeTurismoService pacoteService;

    @Autowired
    private DestinoService destinoService;

    private ClienteDetailDTO criarCliente(String nome, String email, String cpf) {
        return clienteService.salvar(new ClienteRequestDTO(nome, email, cpf, null));
    }

    private PacoteDeTurismoDetailDTO criarPacote(String nome, String nomeDestino) {
        DestinoDetailDTO destino = destinoService.salvar(
                new DestinoRequestDTO(nomeDestino, "Desc", 5, "Natureza", "Brasil"));
        return pacoteService.salvar(new PacoteDeTurismoRequestDTO(nome, 2000.00, destino.id()));
    }

    @Test
    public void testeSalvarReservaComClienteEPacote() {
        ClienteDetailDTO cliente = criarCliente("Ana Paula", "ana@email.com", "12312312399");
        PacoteDeTurismoDetailDTO pacote = criarPacote("Pacote Noronha", "Fernando de Noronha");

        ReservaRequestDTO request = new ReservaRequestDTO("2026-07-20", cliente.id(), pacote.id());
        ReservaDetailDTO salva = reservaService.salvar(request);

        Assertions.assertNotNull(salva.id());
        Assertions.assertEquals(cliente.id(), salva.cliente().id());
        Assertions.assertEquals(pacote.id(), salva.pacote().id());
    }

    @Test
    public void testeAtualizarReserva() {
        ClienteDetailDTO cliente = criarCliente("João Silva", "joao@email.com", "98798798711");
        PacoteDeTurismoDetailDTO pacote = criarPacote("Pacote Bonito", "Bonito");

        ReservaDetailDTO salva = reservaService.salvar(
                new ReservaRequestDTO("2026-01-10", cliente.id(), pacote.id()));

        ReservaDetailDTO atualizada = reservaService.atualizar(salva.id(),
                new ReservaRequestDTO("2026-02-15", cliente.id(), pacote.id()));

        Assertions.assertEquals("2026-02-15", atualizada.dataReserva());
    }

    @Test
    public void testeDeletarReserva() {
        ClienteDetailDTO cliente = criarCliente("Maria Souza", "maria@email.com", "33344455566");
        PacoteDeTurismoDetailDTO pacote = criarPacote("Pacote Chapada", "Chapada Diamantina");

        ReservaDetailDTO salva = reservaService.salvar(
                new ReservaRequestDTO("2026-03-23", cliente.id(), pacote.id()));

        reservaService.excluir(salva.id());

        Assertions.assertThrows(ResponseStatusException.class, () -> reservaService.obterPorId(salva.id()));
    }
}