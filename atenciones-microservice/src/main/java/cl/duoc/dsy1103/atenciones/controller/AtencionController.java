package cl.duoc.dsy1103.atenciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.dsy1103.atenciones.dto.AtencionRequest;
import cl.duoc.dsy1103.atenciones.dto.AtencionResponse;
import cl.duoc.dsy1103.atenciones.service.AtencionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador para gestionar las operaciones relacionadas con las atenciones.
 */
@RestController
@RequestMapping("/v1/atenciones")
@Slf4j
public class AtencionController {

    @Autowired
    private AtencionService atencionService;

    /**
     * Endpoint para obtener la lista de atenciones asociadas a un paciente
     * específico.
     * 
     * @param pacienteId El ID del paciente.
     * @return Una lista de atenciones asociadas al paciente.
     */
    @GetMapping("/paciente/{pacienteId}")
    public List<AtencionResponse> obtenerAtencionesPorPaciente(@PathVariable Long pacienteId) {
        log.info("GET /api/atenciones/paciente/{}", pacienteId);
        return atencionService.obtenerAtencionesPorPacienteId(pacienteId);
    }

    /**
     * Endpoint para crear una nueva atención.
     * 
     * @param atencionRequest La solicitud para crear una nueva atención.
     * @return La atención creada.
     */
    @PostMapping
    public ResponseEntity<AtencionResponse> crearAtencion(@Valid @RequestBody AtencionRequest atencionRequest) {
        log.info("POST /api/atenciones");
        AtencionResponse atencionResponse = atencionService.crearAtencion(atencionRequest);
        return new ResponseEntity<>(atencionResponse, HttpStatus.CREATED);
    }

    /**
     * Endpoint para eliminar una atención específica.
     * 
     * @param id El ID de la atención a eliminar.
     * @return Una respuesta HTTP sin contenido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAtencion(@PathVariable Long id) {
        log.info("DELETE /api/atenciones/{}", id);
        atencionService.eliminarAtencion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
