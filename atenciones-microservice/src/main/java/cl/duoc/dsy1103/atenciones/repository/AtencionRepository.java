package cl.duoc.dsy1103.atenciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.dsy1103.atenciones.model.Atencion;

/**
 * Repositorio para la entidad Atencion.
 * Proporciona métodos CRUD y consultas personalizadas para gestionar las
 * atenciones.
 */
@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    /**
     * Encuentra todas las atenciones asociadas a un paciente específico.
     *
     * @param pacienteId El ID del paciente.
     * @return Una lista de atenciones asociadas al paciente.
     */
    List<Atencion> findAllByPacienteId(Long pacienteId);
}
