package br.edu.ifpr.bsi.ProjetoProgSofApp.repositories;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.PacoteDeTurismo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacoteDeTurismoRepository extends JpaRepository<PacoteDeTurismo, Long> {
    List<PacoteDeTurismo> findByPrecoLessThanEqual(Double valorMaximo);
}