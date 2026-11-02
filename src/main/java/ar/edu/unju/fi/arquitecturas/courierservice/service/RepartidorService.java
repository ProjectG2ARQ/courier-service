package ar.edu.unju.fi.arquitecturas.courierservice.service;

import ar.edu.unju.fi.arquitecturas.courierservice.dto.RepartidorRequestDto;
import ar.edu.unju.fi.arquitecturas.courierservice.dto.RepartidorResponseDto;
import ar.edu.unju.fi.arquitecturas.courierservice.model.Repartidor;
import ar.edu.unju.fi.arquitecturas.courierservice.model.enums.EstadoRepartidor;

import java.util.List;
import java.util.UUID;

public interface RepartidorService {

    RepartidorResponseDto guardarRepartidor(RepartidorRequestDto requestDto);

    RepartidorResponseDto actualizarEstadoRepartidor(UUID id, RepartidorRequestDto requestDto);

    List<RepartidorResponseDto> obtenerTodosLosRepartidores();

    RepartidorResponseDto buscarRepartidorPorId(UUID id);

    Repartidor buscarEntidadPorId(UUID id);

    List<RepartidorResponseDto> findByEstado(EstadoRepartidor estado);
}