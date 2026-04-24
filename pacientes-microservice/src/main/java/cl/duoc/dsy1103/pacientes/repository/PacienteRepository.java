package cl.duoc.dsy1103.pacientes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.dsy1103.pacientes.model.Paciente;

/**
 * Interfaz que extiende JpaRepository para proporcionar métodos de acceso a
 * datos para la entidad Paciente. Esta interfaz permite realizar operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos para los
 * pacientes. Además, se definen métodos personalizados para buscar pacientes
 * por su RUN y verificar la existencia de un paciente con un RUN específico. Al
 * extender JpaRepository, esta interfaz hereda métodos como save(), findById(),
 * findAll(), deleteById(), entre otros, que facilitan la manipulación de los
 * datos de los pacientes en la base de datos. La anotación @Repository indica
 * que esta interfaz es un componente de acceso a datos en el contexto de
 * Spring, lo que permite que Spring maneje las excepciones de la base de datos
 * y realice la inyección de dependencias de manera adecuada.
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    /**
     * Busca un paciente por su RUN. Si no se encuentra, devuelve un Optional vacío.
     *
     * @param run El RUN del paciente a buscar.
     * @return Un Optional que contiene el paciente si se encuentra, o un Optional
     *         vacío si no se encuentra.
     */
    Optional<Paciente> findByRun(String run);

    /**
     * Verifica si existe un paciente con el RUN dado.
     *
     * @param run El RUN del paciente a verificar.
     * @return true si existe un paciente con el RUN dado, false en caso contrario.
     */
    boolean existsByRun(String run);

    /**
     * Verifica si existe un paciente con el RUN dado, excluyendo un paciente con
     * un ID específico. Esto es útil para validar la unicidad del RUN al actualizar
     * un paciente.
     *
     * @param run El RUN del paciente a verificar.
     * @param id  El ID del paciente a excluir de la verificación.
     * @return true si existe un paciente con el RUN dado y un ID diferente al
     *         especificado, false en caso contrario.
     */
    boolean existsByRunAndIdNot(String run, Long id);
}
