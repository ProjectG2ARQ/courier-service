package ar.edu.unju.fi.arquitecturas.courierservice.service.impl;

import ar.edu.unju.fi.arquitecturas.courierservice.dto.RepartidorRequestDto;
import ar.edu.unju.fi.arquitecturas.courierservice.dto.RepartidorResponseDto;
import ar.edu.unju.fi.arquitecturas.courierservice.exception.NegocioException;
import ar.edu.unju.fi.arquitecturas.courierservice.exception.RecursoNoEncontradoException;
import ar.edu.unju.fi.arquitecturas.courierservice.model.Repartidor;
import ar.edu.unju.fi.arquitecturas.courierservice.model.enums.EstadoRepartidor;
import ar.edu.unju.fi.arquitecturas.courierservice.repository.RepartidorRepository;
import ar.edu.unju.fi.arquitecturas.courierservice.service.RepartidorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepartidorServiceImpl implements RepartidorService {

    private final RepartidorRepository repartidorRepository;

    @Override
    @Transactional
    public RepartidorResponseDto guardarRepartidor(RepartidorRequestDto requestDto) {
        if (repartidorRepository.existsByNombreIgnoreCase(requestDto.getNombre())) {
            throw new NegocioException("Ya existe un repartidor registrado con el nombre: " + requestDto.getNombre());
        }

        Repartidor repartidor = Repartidor.builder()
                .nombre(requestDto.getNombre())
                .tipoVehiculo(requestDto.getTipoVehiculo())
                .estado(requestDto.getEstado())
                .build();

        Repartidor repartidorGuardado = repartidorRepository.save(repartidor);
        return toResponseDto(repartidorGuardado);
    }

    @Override
    @Transactional
    public RepartidorResponseDto actualizarEstadoRepartidor(UUID id, RepartidorRequestDto requestDto) {
        Repartidor repartidor = buscarEntidadPorId(id);

        repartidor.setEstado(requestDto.getEstado());

        Repartidor repartidorActualizado = repartidorRepository.save(repartidor);
        return toResponseDto(repartidorActualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RepartidorResponseDto> obtenerTodosLosRepartidores() {
        return repartidorRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RepartidorResponseDto buscarRepartidorPorId(UUID id) {
        Repartidor repartidor = buscarEntidadPorId(id);
        return toResponseDto(repartidor);
    }

    @Override
    @Transactional(readOnly = true)
    public Repartidor buscarEntidadPorId(UUID id) {
        return repartidorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró ningún repartidor con el ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RepartidorResponseDto> findByEstado(EstadoRepartidor estado) {
        return repartidorRepository.findByEstado(estado).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // Método auxiliar para mapear de Entidad a ResponseDto
    private RepartidorResponseDto toResponseDto(Repartidor repartidor) {
        return RepartidorResponseDto.builder()
                .id(repartidor.getId())
                .nombre(repartidor.getNombre())
                .tipoVehiculo(repartidor.getTipoVehiculo())
                .estado(repartidor.getEstado())
                .build();
    }
}