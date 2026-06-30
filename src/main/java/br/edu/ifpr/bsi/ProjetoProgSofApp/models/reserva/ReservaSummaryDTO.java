package br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva;

public record ReservaSummaryDTO(
        Long id,
        String dataReserva,
        Long clienteId,
        Long pacoteId
) {
}