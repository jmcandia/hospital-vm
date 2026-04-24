package cl.duoc.dsy1103.pacientes.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la solicitud de actualización de un paciente. Esta clase
 * se utiliza para recibir los datos del paciente desde el cliente y validar que
 * los datos sean correctos antes de procesarlos. La clase tiene los atributos
 * necesarios para representar la información de un paciente y las anotaciones
 * de validación correspondientes. A diferencia de PacienteRequest, en esta
 * clase los campos no son obligatorios, ya que se pueden actualizar solo
 * algunos campos del paciente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteUpdateRequest {
    @Size(min = 2, max = 100, message = "Los nombres deben tener entre 2 y 100 caracteres")
    private String nombres;

    @Size(min = 2, max = 100, message = "Los apellidos deben tener entre 2 y 100 caracteres")
    private String apellidos;

    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
    private LocalDateTime fechaNacimiento;

    @Email(message = "El correo debe ser válido")
    @Size(max = 150, message = "El correo no puede superar los 150 caracteres")
    private String correo;
}
