package br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino;

public record DestinoDetailDTO(
        Long id,
        String nome,
        String descricao,
        Integer duracaoDeDias,
        String categoria,
        String pais,
        String imagemUrl
) {
}