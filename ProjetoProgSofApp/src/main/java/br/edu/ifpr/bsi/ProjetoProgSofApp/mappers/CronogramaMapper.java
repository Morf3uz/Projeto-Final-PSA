package br.edu.ifpr.bsi.ProjetoProgSofApp.mappers;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Cronograma;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma.CronogramaDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma.CronogramaRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cronograma.CronogramaSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PacoteDeTurismoMapper.class})
public interface CronogramaMapper {

    @Mapping(target = "pacote", ignore = true)
    Cronograma requestDTOToEntity(CronogramaRequestDTO cronogramaRequestDTO);

    CronogramaDetailDTO entityToDetailDTO(Cronograma cronograma);

    CronogramaSummaryDTO entityToSummaryDTO(Cronograma cronograma);
}