package cl.duoc.dsy1103.atenciones.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta de un paciente. Esta clase se utiliza para
 * enviar los datos del paciente al cliente después de procesar una solicitud.
 * La clase tiene los atributos necesarios para representar la información de un
 * paciente y se utiliza para mapear los datos desde la entidad Paciente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteResponse {
    private Long id;
    private String run;
    private String nombres;
    private String apellidos;
    private LocalDateTime fechaNacimiento;
    private String correo;
}
