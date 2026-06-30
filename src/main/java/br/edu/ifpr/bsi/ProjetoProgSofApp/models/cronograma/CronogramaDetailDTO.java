package br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoSummaryDTO;

public record CronogramaDetailDTO(
        Long id,
        String descricao,
        String horario,
        PacoteDeTurismoSummaryDTO pacote
) {
}