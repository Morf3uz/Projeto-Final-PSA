package br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoSummaryDTO;

public record PacoteDeTurismoDetailDTO(
        Long id,
        String nome,
        Double preco,
        DestinoSummaryDTO destino
) {
}