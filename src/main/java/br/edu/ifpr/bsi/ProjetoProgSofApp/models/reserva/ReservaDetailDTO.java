package br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteSummaryDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoSummaryDTO;

public record ReservaDetailDTO(
        Long id,
        String dataReserva,
        ClienteSummaryDTO cliente,
        PacoteDeTurismoSummaryDTO pacote
) {
}