package br.edu.ifpr.bsi.ProjetoProgSofApp.repositories;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {
    List<Destino> findByCategoria(String categoria);
}