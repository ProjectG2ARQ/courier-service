package ar.edu.unju.fi.arquitecturas.courierservice.repository;

import ar.edu.unju.fi.arquitecturas.courierservice.model.Repartidor;
import ar.edu.unju.fi.arquitecturas.courierservice.model.enums.EstadoRepartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor, UUID> {

    List<Repartidor> findByEstado(EstadoRepartidor estado);

    boolean existsByNombreIgnoreCase(String nombre);
}