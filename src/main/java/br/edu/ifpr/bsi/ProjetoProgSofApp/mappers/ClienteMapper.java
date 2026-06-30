package br.edu.ifpr.bsi.ProjetoProgSofApp.mappers;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Cliente;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "isAdmin", ignore = true)
    Cliente requestDTOToEntity(ClienteRequestDTO clienteRequestDTO);

    ClienteDetailDTO entityToDetailDTO(Cliente cliente);

    ClienteSummaryDTO entityToSummaryDTO(Cliente cliente);
}