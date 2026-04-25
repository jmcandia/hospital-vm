package cl.duoc.dsy1103.atenciones.client;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import cl.duoc.dsy1103.atenciones.dto.PacienteResponse;
import cl.duoc.dsy1103.atenciones.exception.ForbiddenException;
import cl.duoc.dsy1103.atenciones.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;

/**
 * Cliente para comunicarse con el microservicio de pacientes.
 * Proporciona métodos para obtener información de pacientes utilizando
 * WebClient.
 * Maneja las respuestas y errores de las llamadas HTTP al servicio de
 * pacientes.
 */
@Component
@Slf4j
public class PacienteClient {

    @Autowired
    private WebClient webClient;

    /**
     * Obtiene la información de un paciente por su RUN.
     * Realiza una llamada HTTP GET al servicio de pacientes para obtener los
     * datos del paciente.
     * Maneja las respuestas y errores de la llamada HTTP, lanzando excepciones
     * específicas según el código de estado HTTP recibido.
     *
     * @param run El RUN del paciente a obtener.
     * @return Un objeto PacienteResponse con la información del paciente.
     * @throws UnauthorizedException  Si el servicio de pacientes devuelve un error
     *                                401 (No autorizado).
     * @throws ForbiddenException     Si el servicio de pacientes devuelve un error
     *                                403 (Acceso prohibido).
     * @throws NoSuchElementException Si el servicio de pacientes devuelve un error
     *                                404 (Paciente no encontrado).
     * @throws RuntimeException       Para cualquier otro error que ocurra al
     *                                llamar al servicio de pacientes.
     */
    public PacienteResponse obtenerPacientePorRun(String run) {
        log.info("Obteniendo información del paciente con RUN: {}", run);
        try {
            return webClient.get()
                    .uri("/run/{run}", run)
                    .retrieve()
                    .bodyToMono(PacienteResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error al obtener información del paciente con RUN: {}", run, ex);
            switch (ex.getStatusCode().value()) {
                case 401 -> throw new UnauthorizedException(
                        "No autorizado para acceder al servicio de pacientes al obtener paciente con RUN: " + run);
                case 403 -> throw new ForbiddenException(
                        "Acceso prohibido al servicio de pacientes al obtener paciente con RUN: " + run);
                case 404 -> throw new NoSuchElementException("Paciente no encontrado con RUN: " + run);
                default -> throw new RuntimeException(
                        "Error interno del servicio de pacientes al obtener paciente con RUN: " + run);
            }
        }
    }
}
