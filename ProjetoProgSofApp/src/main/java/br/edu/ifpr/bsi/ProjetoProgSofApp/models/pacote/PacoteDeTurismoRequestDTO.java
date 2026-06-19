package br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote;

public record PacoteDeTurismoRequestDTO(
        String nome,
        Double preco,
        Long destinoId
) {
}