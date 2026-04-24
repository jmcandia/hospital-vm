package cl.duoc.dsy1103.pacientes.exception;

/**
 * Excepción que se lanza cuando se intenta crear un paciente con un RUN que ya
 * existe.
 */
public class PacienteAlreadyExistsException extends RuntimeException {

    /**
     * Constructor de la excepción.
     * 
     * @param message Mensaje de error.
     */
    public PacienteAlreadyExistsException(String message) {
        super(message);
    }
}
