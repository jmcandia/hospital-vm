package cl.duoc.dsy1103.atenciones.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.duoc.dsy1103.atenciones.validation.ValidRun;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de atención médica. Esta clase se utiliza
 * para recibir la información de una nueva atención médica a través de una
 * solicitud HTTP. La clase tiene los atributos necesarios para representar la
 * información de una atención médica, como la fecha de la atención, el costo,
 * un comentario y el ID del paciente asociado a la atención. La clase también
 * tiene las anotaciones de validación necesarias para asegurar que la
 * información recibida sea válida antes de ser procesada por el servicio.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtencionRequest {
    @NotNull(message = "La fecha de atención es obligatoria")
    @FutureOrPresent(message = "La fecha de atención no puede ser pasada")
    private LocalDateTime fechaAtencion;

    @NotNull(message = "El costo es obligatorio")
    @Min(value = 1, message = "El costo debe ser mayor a 0")
    private BigDecimal costo;

    @Size(max = 255, message = "El comentario no puede superar los 255 caracteres")
    private String comentario;

    @NotBlank(message = "El RUN del paciente es obligatorio")
    @Size(min = 8, max = 10, message = "El RUN del paciente debe tener entre 8 y 10 caracteres")
    @ValidRun(message = "El RUN del paciente debe ser válido y seguir el formato correcto")
    private String runPaciente;
}
