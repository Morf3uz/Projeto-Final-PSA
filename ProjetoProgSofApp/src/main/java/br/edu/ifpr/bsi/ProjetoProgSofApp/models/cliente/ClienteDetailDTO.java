package br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente;

public record ClienteDetailDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        Boolean isAdmin
) {
}