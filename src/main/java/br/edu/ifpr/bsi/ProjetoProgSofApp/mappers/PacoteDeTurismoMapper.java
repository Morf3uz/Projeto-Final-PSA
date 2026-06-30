package br.edu.ifpr.bsi.ProjetoProgSofApp.mappers;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.PacoteDeTurismo;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.pacote.PacoteDeTurismoSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DestinoMapper.class})
public interface PacoteDeTurismoMapper {

    @Mapping(target = "destino", ignore = true)
    PacoteDeTurismo requestDTOToEntity(PacoteDeTurismoRequestDTO pacoteRequestDTO);

    PacoteDeTurismoDetailDTO entityToDetailDTO(PacoteDeTurismo pacote);

    PacoteDeTurismoSummaryDTO entityToSummaryDTO(PacoteDeTurismo pacote);
}