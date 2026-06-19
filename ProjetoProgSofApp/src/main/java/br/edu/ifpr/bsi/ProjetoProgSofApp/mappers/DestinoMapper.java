package br.edu.ifpr.bsi.ProjetoProgSofApp.mappers;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Destino;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.destino.DestinoSummaryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DestinoMapper {

    Destino requestDTOToEntity(DestinoRequestDTO destinoRequestDTO);

    DestinoDetailDTO entityToDetailDTO(Destino destino);

    DestinoSummaryDTO entityToSummaryDTO(Destino destino);
}