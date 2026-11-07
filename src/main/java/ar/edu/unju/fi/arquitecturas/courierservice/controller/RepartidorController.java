package ar.edu.unju.fi.arquitecturas.courierservice.controller;

import ar.edu.unju.fi.arquitecturas.courierservice.dto.RepartidorRequestDto;
import ar.edu.unju.fi.arquitecturas.courierservice.dto.RepartidorResponseDto;
import ar.edu.unju.fi.arquitecturas.courierservice.exception.NegocioException;
import ar.edu.unju.fi.arquitecturas.courierservice.exception.RecursoNoEncontradoException;
import ar.edu.unju.fi.arquitecturas.courierservice.model.Repartidor;
import ar.edu.unju.fi.arquitecturas.courierservice.model.enums.EstadoRepartidor;
import ar.edu.unju.fi.arquitecturas.courierservice.repository.RepartidorRepository;
import ar.edu.unju.fi.arquitecturas.courierservice.service.RepartidorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/repartidores")
@RequiredArgsConstructor
@Tag(name = "Repartidores", description = "Endpoints para la gestión de repartidores")
public class RepartidorController {

    private final RepartidorService repartidorService;

    @PostMapping
    @Operation(summary = "Registrar un nuevo repartidor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Repartidor registrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o nombre duplicado")
    })
    public ResponseEntity<RepartidorResponseDto> guardarRepartidor(@Valid @RequestBody RepartidorRequestDto repartidorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repartidorService.guardarRepartidor(repartidorRequestDto));
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Actualizar el estado de un repartidor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Repartidor no encontrado")
    })
    public ResponseEntity<RepartidorResponseDto> actualizarEstadoRepartidor(
            @PathVariable UUID id,
            @Valid @RequestBody RepartidorRequestDto repartidorRequestDto) {
        return ResponseEntity.ok(repartidorService.actualizarEstadoRepartidor(id, repartidorRequestDto));
    }

    @GetMapping
    @Operation(summary = "Obtener todos los repartidores")
    public ResponseEntity<List<RepartidorResponseDto>> obtenerTodosLosRepartidores() {
        return ResponseEntity.ok(repartidorService.obtenerTodosLosRepartidores());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un repartidor por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Repartidor encontrado"),
            @ApiResponse(responseCode = "404", description = "Repartidor no encontrado")
    })
    public ResponseEntity<RepartidorResponseDto> buscarRepartidorPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(repartidorService.buscarRepartidorPorId(id));
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Filtrar repartidores por estado")
    public ResponseEntity<List<RepartidorResponseDto>> obtenerPorEstado(@PathVariable EstadoRepartidor estado) {
        return ResponseEntity.ok(repartidorService.findByEstado(estado));
    }

    @GetMapping("/mi-perfil")
    @Operation(summary = "Obtener los datos operativos del repartidor logueado")
    public ResponseEntity<RepartidorResponseDto> obtenerMiPerfil(
            @RequestHeader(value = "X-Auth-User-Id", required = false) Long userId) {
        if (userId == null) {
            throw new NegocioException("Falta la cabecera de autenticación de usuario.");
        }
        return ResponseEntity.ok(repartidorService.buscarPorUserId(userId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar lógicamente un repartidor (Soft Delete)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Repartidor dado de baja con éxito"),
            @ApiResponse(responseCode = "404", description = "Repartidor no encontrado")
    })
    public ResponseEntity<Void> eliminarRepartidor(@PathVariable UUID id) {
        repartidorService.eliminarRepartidor(id);
        return ResponseEntity.noContent().build();
    }
}