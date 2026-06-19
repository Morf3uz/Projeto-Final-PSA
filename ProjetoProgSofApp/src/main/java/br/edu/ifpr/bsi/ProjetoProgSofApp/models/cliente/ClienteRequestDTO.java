package br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente;

public record ClienteRequestDTO(
        String nome,
        String email,
        String cpf,
        String senha
) {
}