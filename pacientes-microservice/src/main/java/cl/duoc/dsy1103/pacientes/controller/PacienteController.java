package cl.duoc.dsy1103.pacientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.dsy1103.pacientes.dto.AtencionResponse;
import cl.duoc.dsy1103.pacientes.dto.PacienteRequest;
import cl.duoc.dsy1103.pacientes.dto.PacienteResponse;
import cl.duoc.dsy1103.pacientes.dto.PacienteUpdateRequest;
import cl.duoc.dsy1103.pacientes.model.Paciente;
import cl.duoc.dsy1103.pacientes.service.PacienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador para gestionar las operaciones relacionadas con los pacientes.
 */
@RestController
@RequestMapping("/v1/pacientes")
@Slf4j
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    /**
     * Endpoint para obtener la lista de pacientes registrados.
     * 
     * @return Lista de pacientes. Si no hay pacientes, devuelve una lista vacía.
     */
    @GetMapping()
    public List<Paciente> buscarPacientes() {
        log.info("GET /api/pacientes/buscarPacientes");
        return pacienteService.buscarPacientes();
    }

    /**
     * Endpoint para obtener un paciente por su ID.
     * 
     * @param id ID del paciente a buscar.
     * @return Paciente encontrado. Si no se encuentra, devuelve un error 404.
     */
    @GetMapping("/{id}")
    public PacienteResponse buscarPacientePorId(@PathVariable Long id) {
        log.info("GET /api/pacientes/buscarPacientePorId/{}", id);
        return pacienteService.buscarPacientePorId(id);
    }

    /**
     * Endpoint para obtener un paciente por su RUN.
     * 
     * @param run RUN del paciente a buscar.
     * @return Paciente encontrado. Si no se encuentra, devuelve un error NOT_FOUND
     *         404.
     */
    @GetMapping("/run/{run}")
    public PacienteResponse buscarPacientePorRun(@PathVariable String run) {
        log.info("GET /api/pacientes/buscarPacientePorRun/{}", run);
        return pacienteService.buscarPacientePorRun(run);
    }

    /**
     * Endpoint para crear un nuevo paciente. Si el RUN ya existe, devuelve un error
     * CONFLICT 409.
     * 
     * @param request Datos del paciente a crear.
     * @return Paciente creado.
     */
    @PostMapping()
    public ResponseEntity<PacienteResponse> crearPaciente(@Valid @RequestBody PacienteRequest request) {
        log.info("POST /api/pacientes/crearPaciente");
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.crearPaciente(request));
    }

    /**
     * Endpoint para actualizar un paciente existente. Si el paciente no existe,
     * devuelve un error NOT_FOUND 404.
     * 
     * @param id      ID del paciente a actualizar.
     * @param request Datos del paciente a actualizar.
     * @return Paciente actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> actualizarPaciente(@PathVariable Long id,
            @Valid @RequestBody PacienteUpdateRequest request) {
        log.info("PUT /api/pacientes/actualizarPaciente/{}", id);
        return ResponseEntity.ok().body(pacienteService.actualizarPaciente(id, request));
    }

    /**
     * Endpoint para eliminar un paciente por su ID. Si el paciente no existe,
     * devuelve un error NOT_FOUND 404.
     * 
     * @param id ID del paciente a eliminar.
     * @return ResponseEntity<Void> con status NO_CONTENT 204 si se elimina
     *         correctamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        log.info("DELETE /api/pacientes/eliminarPaciente/{}", id);
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para obtener las atenciones de un paciente por su ID. Si el
     * paciente no existe, devuelve un error NOT_FOUND 404.
     * 
     * @param id ID del paciente cuyas atenciones se desean obtener.
     * @return Lista de atenciones del paciente.
     */
    @GetMapping("/{id}/atenciones")
    public List<AtencionResponse> obtenerAtencionesPorPacienteId(@PathVariable Long id) {
        log.info("GET /api/pacientes/{}/atenciones", id);
        return pacienteService.buscarAtencionesPorPacienteId(id);
    }
}
