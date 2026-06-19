package br.edu.ifpr.bsi.ProjetoProgSofApp.repositories;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByDataReserva(String dataReserva);
}