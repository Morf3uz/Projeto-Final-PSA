package br.edu.ifpr.bsi.ProjetoProgSofApp.mappers;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Reserva;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva.ReservaDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva.ReservaRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.reserva.ReservaSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, PacoteDeTurismoMapper.class})
public interface ReservaMapper {

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "pacote", ignore = true)
    Reserva requestDTOToEntity(ReservaRequestDTO reservaRequestDTO);

    ReservaDetailDTO entityToDetailDTO(Reserva reserva);

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "pacote.id", target = "pacoteId")
    ReservaSummaryDTO entityToSummaryDTO(Reserva reserva);
}