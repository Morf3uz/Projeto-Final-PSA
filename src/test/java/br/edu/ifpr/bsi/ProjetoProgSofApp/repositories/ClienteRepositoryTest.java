package br.edu.ifpr.bsi.ProjetoProgSofApp.repositories;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.ClienteService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@SpringBootTest
@Transactional
public class ClienteRepositoryTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testeSalvarCliente() {
        ClienteRequestDTO request = new ClienteRequestDTO("Douglas Vaz", "douglas@email.com", "12345678901", null);

        ClienteDetailDTO salvo = clienteService.salvar(request);

        Assertions.assertNotNull(salvo.id());
        Assertions.assertEquals("Douglas Vaz", salvo.nome());
        Assertions.assertEquals("douglas@email.com", salvo.email());
    }

    @Test
    public void testeListarClientes() {
        ClienteRequestDTO request = new ClienteRequestDTO("Carlos Listagem", "carlos@email.com", "55566677788", null);
        clienteService.salvar(request);

        long inicio = System.currentTimeMillis();
        List<ClienteDetailDTO> clientes = clienteService.listar();
        long fim = System.currentTimeMillis();

        Assertions.assertFalse(clientes.isEmpty());
        Assertions.assertTrue((fim - inicio) < 300);
    }

    @Test
    public void testeBuscarClientePorCpf() {
        ClienteRequestDTO request = new ClienteRequestDTO("Douglas", "douglasbusca@email.com", "99988877766", null);
        clienteService.salvar(request);

        Assertions.assertTrue(clienteService.buscarPorCpf("99988877766").isPresent());
    }

    @Test
    public void testeAtualizarCliente() {
        ClienteRequestDTO request = new ClienteRequestDTO("Nome Original", "original@email.com", "11122233344", null);
        ClienteDetailDTO salvo = clienteService.salvar(request);

        ClienteRequestDTO atualizado = new ClienteRequestDTO("Nome Atualizado", "original@email.com", "11122233344", null);
        ClienteDetailDTO resultado = clienteService.atualizar(salvo.id(), atualizado);

        Assertions.assertEquals("Nome Atualizado", resultado.nome());
    }

    @Test
    public void testeDeletarCliente() {
        ClienteRequestDTO request = new ClienteRequestDTO("Para Deletar", "deletar@email.com", "00011122233", null);
        ClienteDetailDTO salvo = clienteService.salvar(request);

        clienteService.excluir(salvo.id());

        Assertions.assertThrows(ResponseStatusException.class, () -> clienteService.obterPorId(salvo.id()));
    }
}