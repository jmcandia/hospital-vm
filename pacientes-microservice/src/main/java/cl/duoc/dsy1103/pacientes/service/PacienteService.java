package cl.duoc.dsy1103.pacientes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.dsy1103.pacientes.dto.PacienteRequest;
import cl.duoc.dsy1103.pacientes.dto.PacienteResponse;
import cl.duoc.dsy1103.pacientes.dto.PacienteUpdateRequest;
import cl.duoc.dsy1103.pacientes.exception.PacienteAlreadyExistsException;
import cl.duoc.dsy1103.pacientes.mapper.PacienteMapper;
import cl.duoc.dsy1103.pacientes.model.Paciente;
import cl.duoc.dsy1103.pacientes.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que proporciona los servicios para la gestión de pacientes.
 */
@Service
@Transactional
@Slf4j
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteMapper pacienteMapper;

    /**
     * Busca todos los pacientes registrados en la base de datos.
     * 
     * @return Lista de pacientes. Si no hay pacientes, devuelve una lista vacía.
     */
    public List<Paciente> buscarPacientes() {
        log.info("Buscando pacientes...");
        return pacienteRepository.findAll();
    }

    /**
     * Busca un paciente por su ID. Si no se encuentra, lanza una excepción
     * NoSuchElementException.
     * 
     * @param id ID del paciente a buscar.
     * @return Paciente encontrado.
     */
    public PacienteResponse buscarPacientePorId(Long id) {
        log.info("Buscando paciente con ID: {}", id);
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado"));
        return pacienteMapper.toResponse(paciente);
    }

    /**
     * Busca un paciente por su RUN. Si no se encuentra, lanza una excepción
     * NoSuchElementException.
     * 
     * @param run RUN del paciente a buscar.
     * @return Paciente encontrado.
     */
    public PacienteResponse buscarPacientePorRun(String run) {
        log.info("Buscando paciente con RUN: {}", run);
        Paciente paciente = pacienteRepository.findByRun(run)
                .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado"));
        return pacienteMapper.toResponse(paciente);
    }

    /**
     * Crea un nuevo paciente. Si el RUN ya existe, lanza una excepción
     * PacienteAlreadyExistsException.
     * 
     * @param request
     * @return
     */
    public PacienteResponse crearPaciente(PacienteRequest request) {
        log.info("Creando paciente con RUN: {}", request.getRun());
        if (pacienteRepository.existsByRun(request.getRun())) {
            throw new PacienteAlreadyExistsException("El RUN ya existe");
        }
        Paciente paciente = pacienteRepository.save(pacienteMapper.fromRequest(request));
        return pacienteMapper.toResponse(paciente);
    }

    /**
     * Actualiza un paciente existente. Si el paciente no existe, lanza una
     * excepción NoSuchElementException. Si el RUN ya existe para otro paciente,
     * lanza una excepción PacienteAlreadyExistsException.
     * 
     * @param id      ID del paciente a actualizar.
     * @param request Datos del paciente a actualizar.
     * @return Paciente actualizado.
     */
    public PacienteResponse actualizarPaciente(Long id, PacienteUpdateRequest request) {
        log.info("Actualizando paciente con ID: {}", id);
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado"));

        if (request.getNombres() != null) {
            paciente.setNombres(request.getNombres());
        }
        if (request.getApellidos() != null) {
            paciente.setApellidos(request.getApellidos());
        }
        if (request.getFechaNacimiento() != null) {
            paciente.setFechaNacimiento(request.getFechaNacimiento());
        }
        if (request.getCorreo() != null) {
            paciente.setCorreo(request.getCorreo());
        }
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.toResponse(paciente);
    }

    /**
     * Elimina un paciente por su ID. Si el paciente no existe, lanza una
     * excepción NoSuchElementException.
     * 
     * @param id ID del paciente a eliminar.
     */
    public void eliminarPaciente(Long id) {
        log.info("Eliminando paciente con ID: {}", id);
        if (!pacienteRepository.existsById(id)) {
            throw new NoSuchElementException("Paciente no encontrado");
        }
        pacienteRepository.deleteById(id);
    }
}
