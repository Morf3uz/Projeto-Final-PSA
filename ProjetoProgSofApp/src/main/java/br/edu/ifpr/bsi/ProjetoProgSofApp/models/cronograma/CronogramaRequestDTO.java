package br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma;

public record CronogramaRequestDTO(
        String descricao,
        String horario,
        Long pacoteId
) {
}