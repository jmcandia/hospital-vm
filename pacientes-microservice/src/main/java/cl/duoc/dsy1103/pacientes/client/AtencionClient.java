package cl.duoc.dsy1103.pacientes.client;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import cl.duoc.dsy1103.pacientes.dto.AtencionResponse;
import cl.duoc.dsy1103.pacientes.exception.ForbiddenException;
import cl.duoc.dsy1103.pacientes.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;

/**
 * Cliente para comunicarse con el microservicio de atenciones.
 * Proporciona métodos para obtener información de atenciones utilizando
 * WebClient.
 * Maneja las respuestas y errores de las llamadas HTTP al servicio de
 * atenciones.
 */
@Component
@Slf4j
public class AtencionClient {
    @Autowired
    private WebClient webClient;

    /**
     * Obtiene la información de las atenciones de un paciente por su ID.
     * Realiza una llamada HTTP GET al servicio de atenciones para obtener los
     * datos de las atenciones del paciente.
     * Maneja las respuestas y errores de la llamada HTTP, lanzando excepciones
     * específicas según el código de estado HTTP recibido.
     *
     * @param id El ID del paciente cuyas atenciones se desean obtener.
     * @return Una lista de objetos AtencionResponse con la información de las
     *         atenciones del paciente.
     * @throws UnauthorizedException  Si el servicio de atenciones devuelve un error
     *                                401 (No autorizado).
     * @throws ForbiddenException     Si el servicio de atenciones devuelve un error
     *                                403 (Acceso prohibido).
     * @throws NoSuchElementException Si el servicio de atenciones devuelve un error
     *                                404 (Atenciones no encontradas).
     * @throws RuntimeException       Para cualquier otro error que ocurra al
     *                                llamar al servicio de atenciones.
     */
    public List<AtencionResponse> obtenerAtencionPorPacienteId(Long id) {
        log.info("Obteniendo información de las atenciones del paciente con ID: {}", id);
        try {
            return webClient.get()
                    .uri("/paciente/{id}", id)
                    .retrieve()
                    .bodyToFlux(AtencionResponse.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error al obtener información del paciente con ID: {}", id, ex);
            switch (ex.getStatusCode().value()) {
                case 401 -> throw new UnauthorizedException(
                        "No autorizado para acceder al servicio de atenciones y obtener información del paciente ID: "
                                + id);
                case 403 -> throw new ForbiddenException(
                        "Acceso prohibido al servicio de atenciones y obtener información del paciente ID: " + id);
                default -> throw new RuntimeException(
                        "Error interno del servicio de atenciones al obtener información del paciente con ID: " + id);
            }
        }
    }
}
