package cl.duoc.dsy1103.pacientes.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la solicitud de creación o actualización de un paciente.
 * Esta clase se utiliza para recibir los datos del paciente desde el cliente y
 * validar que los datos sean correctos antes de procesarlos. La clase tiene los
 * atributos necesarios para representar la información de un paciente y las
 * anotaciones de validación correspondientes.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequest {
    @NotBlank(message = "El RUN es obligatorio")
    @Size(min = 9, max = 10, message = "El RUN debe tener entre 9 y 10 caracteres")
    private String run;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(min = 2, max = 100, message = "Los nombres deben tener entre 2 y 100 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 2, max = 100, message = "Los apellidos deben tener entre 2 y 100 caracteres")
    private String apellidos;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
    private LocalDateTime fechaNacimiento;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    @Size(max = 150, message = "El correo no puede superar los 150 caracteres")
    private String correo;
}
