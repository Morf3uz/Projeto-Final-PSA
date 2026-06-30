package br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva;

public record ReservaRequestDTO(
        String dataReserva,
        Long clienteId,
        Long pacoteId
) {
}