package cl.duoc.dsy1103.atenciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.dsy1103.atenciones.client.PacienteClient;
import cl.duoc.dsy1103.atenciones.dto.AtencionRequest;
import cl.duoc.dsy1103.atenciones.dto.AtencionResponse;
import cl.duoc.dsy1103.atenciones.dto.PacienteResponse;
import cl.duoc.dsy1103.atenciones.mapper.AtencionMapper;
import cl.duoc.dsy1103.atenciones.model.Atencion;
import cl.duoc.dsy1103.atenciones.repository.AtencionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que proporciona los servicios para la gestión de atenciones.
 */
@Service
@Transactional
@Slf4j
public class AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private AtencionMapper atencionMapper;

    @Autowired
    private PacienteClient pacienteClient;

    /**
     * Crea una nueva atención para un paciente.
     *
     * @param atencionRequest La solicitud de creación de atención que contiene el
     *                        RUN del paciente y los detalles de la atención.
     * @return La respuesta que contiene los detalles de la atención creada.
     */
    public AtencionResponse crearAtencion(AtencionRequest atencionRequest, String token) {
        log.info("Creando nueva atención para el paciente RUN: {}",
                atencionRequest.getRunPaciente());
        // 1. Consultar al microservicio de pacientes para obtener el ID del paciente
        PacienteResponse pacienteResponse = pacienteClient.obtenerPacientePorRun(atencionRequest.getRunPaciente(),
                token);
        // 2. Crear la atención utilizando el ID del paciente obtenido
        Atencion atencion = atencionMapper.fromRequest(atencionRequest);
        atencion.setPacienteId(pacienteResponse.getId());
        // 3. Guardar la atención en la base de datos
        Atencion savedAtencion = atencionRepository.save(atencion);
        // 4. Convertir la atención guardada a AtencionResponse y devolverla
        return atencionMapper.toResponse(savedAtencion);
    }

    /**
     * Obtiene todas las atenciones asociadas a un paciente específico.
     *
     * @param pacienteId El ID del paciente para el cual se desean obtener las
     *                   atenciones.
     * @return Una lista de respuestas que contienen los detalles de las
     *         atenciones del paciente.
     */
    public List<AtencionResponse> obtenerAtencionesPorPacienteId(Long pacienteId) {
        log.info("Obteniendo atenciones para el paciente ID: {}", pacienteId);
        // 1. Obtener las atenciones del paciente utilizando el ID obtenido
        List<Atencion> atenciones = atencionRepository.findAllByPacienteId(pacienteId);
        // 2. Convertir la lista de atenciones a una lista de AtencionResponse y
        // devolverla
        return atenciones.stream()
                .map(atencionMapper::toResponse)
                .toList();
    }

    /**
     * Elimina una atención por su ID.
     *
     * @param id El ID de la atención que se desea eliminar.
     */
    public void eliminarAtencion(Long id) {
        log.info("Eliminando atención con ID: {}", id);
        atencionRepository.deleteById(id);
    }
}
