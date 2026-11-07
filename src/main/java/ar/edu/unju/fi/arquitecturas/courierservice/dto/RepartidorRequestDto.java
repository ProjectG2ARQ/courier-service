package ar.edu.unju.fi.arquitecturas.courierservice.dto;

import ar.edu.unju.fi.arquitecturas.courierservice.model.enums.EstadoRepartidor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepartidorRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    private String tipoVehiculo;

    @NotNull(message = "El estado es obligatorio")
    private EstadoRepartidor estado;

    @NotNull(message = "El ID es obligatorio")
    private Long userId;
}