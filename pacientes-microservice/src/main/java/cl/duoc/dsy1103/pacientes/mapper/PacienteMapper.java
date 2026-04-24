package cl.duoc.dsy1103.pacientes.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.dsy1103.pacientes.dto.PacienteRequest;
import cl.duoc.dsy1103.pacientes.dto.PacienteResponse;
import cl.duoc.dsy1103.pacientes.model.Paciente;

@Component
public class PacienteMapper {

    /**
     * Convierte un PacienteRequest a un Paciente. No se asigna el ID, ya que este
     * se genera automáticamente al guardar el paciente en la base de datos. El
     * método utiliza el patrón builder para crear una instancia de Paciente a
     * partir de los datos del PacienteRequest. El método asigna el RUN, nombres,
     * apellidos, fecha de nacimiento y correo del Paciente a partir de los datos
     * del PacienteRequest.
     * 
     * @param request El PacienteRequest a convertir.
     * @return Un Paciente con los datos del PacienteRequest.
     */
    public Paciente fromRequest(PacienteRequest request) {
        return Paciente.builder()
                .run(request.getRun())
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .fechaNacimiento(request.getFechaNacimiento())
                .correo(request.getCorreo())
                .build();
    }

    /**
     * Convierte un Paciente a un PacienteResponse. El método utiliza el patrón
     * builder para crear una instancia de PacienteResponse a partir de los datos
     * del Paciente. El método asigna el ID, RUN, nombres, apellidos, fecha de
     * nacimiento y correo del Paciente a partir de los datos del Paciente. Este
     * método se utiliza para mapear los datos de un Paciente a un formato que se
     * pueda enviar al cliente después de procesar una solicitud. El
     * PacienteResponse se utiliza para enviar los datos del paciente al cliente de
     * una manera estructurada y fácil de entender.
     * 
     * @param paciente El Paciente a convertir.
     * @return Un PacienteResponse con los datos del Paciente.
     */
    public PacienteResponse toResponse(Paciente paciente) {
        return PacienteResponse.builder()
                .id(paciente.getId())
                .run(paciente.getRun())
                .nombres(paciente.getNombres())
                .apellidos(paciente.getApellidos())
                .fechaNacimiento(paciente.getFechaNacimiento())
                .correo(paciente.getCorreo())
                .build();
    }
}
