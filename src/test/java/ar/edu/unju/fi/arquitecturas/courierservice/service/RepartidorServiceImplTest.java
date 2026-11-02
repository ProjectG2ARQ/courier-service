package ar.edu.unju.fi.arquitecturas.courierservice.service;

import ar.edu.unju.fi.arquitecturas.courierservice.dto.RepartidorRequestDto;
import ar.edu.unju.fi.arquitecturas.courierservice.dto.RepartidorResponseDto;
import ar.edu.unju.fi.arquitecturas.courierservice.exception.RecursoNoEncontradoException;
import ar.edu.unju.fi.arquitecturas.courierservice.model.Repartidor;
import ar.edu.unju.fi.arquitecturas.courierservice.model.enums.EstadoRepartidor;
import ar.edu.unju.fi.arquitecturas.courierservice.repository.RepartidorRepository;
import ar.edu.unju.fi.arquitecturas.courierservice.service.impl.RepartidorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepartidorServiceImplTest {

    @Mock
    private RepartidorRepository repartidorRepository;

    @InjectMocks
    private RepartidorServiceImpl repartidorService;

    private Repartidor repartidor;
    private UUID repartidorId;

    @BeforeEach
    void setUp() {
        repartidorId = UUID.randomUUID();
        repartidor = Repartidor.builder()
                .nombre("Carlos Gomez")
                .tipoVehiculo("MOTO")
                .estado(EstadoRepartidor.ACTIVO)
                .build();
        repartidor.setId(repartidorId);
    }

    @Test
    void actualizarEstadoRepartidor_Exito() {
        RepartidorRequestDto requestDto = RepartidorRequestDto.builder()
                .estado(EstadoRepartidor.INACTIVO)
                .build();

        when(repartidorRepository.findById(repartidorId)).thenReturn(Optional.of(repartidor));
        when(repartidorRepository.save(any(Repartidor.class))).thenReturn(repartidor);

        RepartidorResponseDto response = repartidorService.actualizarEstadoRepartidor(repartidorId, requestDto);

        assertNotNull(response);
        assertEquals(EstadoRepartidor.INACTIVO, response.getEstado());
        verify(repartidorRepository, times(1)).findById(repartidorId);
        verify(repartidorRepository, times(1)).save(any(Repartidor.class));
    }

    @Test
    void actualizarEstadoRepartidor_NoEncontrado_LanzaExcepcion() {
        RepartidorRequestDto requestDto = RepartidorRequestDto.builder()
                .estado(EstadoRepartidor.ACTIVO)
                .build();

        when(repartidorRepository.findById(repartidorId)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> {
            repartidorService.actualizarEstadoRepartidor(repartidorId, requestDto);
        });

        verify(repartidorRepository, times(1)).findById(repartidorId);
        verify(repartidorRepository, never()).save(any(Repartidor.class));
    }
}
