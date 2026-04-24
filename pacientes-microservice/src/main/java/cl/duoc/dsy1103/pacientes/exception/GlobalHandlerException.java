package cl.duoc.dsy1103.pacientes.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que maneja las excepciones globales en la aplicación. Esta clase se
 * utiliza para capturar y procesar las excepciones que ocurren en los
 * controladores y devolver respuestas adecuadas al cliente.
 */
@RestControllerAdvice
@Slf4j
public class GlobalHandlerException {

    /**
     * Maneja la excepción NoSuchElementException lanzada cuando no se encuentra un
     * paciente en la base de datos. Devuelve una respuesta con el error y un
     * mensaje descriptivo. El mensaje de error se obtiene de la excepción lanzada
     * en el servicio. El mensaje de error se incluye en la lista de errores de la
     * respuesta.
     * 
     * @param ex      La excepción NoSuchElementException lanzada.
     * @param request La solicitud HTTP que causó la excepción.
     * @return Una respuesta con el error y un mensaje descriptivo.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponse> handleNoSuchElementException(NoSuchElementException ex,
            HttpServletRequest request) {
        log.error("Registro no encontrada: {}", ex.getMessage());
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Maneja la excepción PacienteAlreadyExistsException lanzada cuando se intenta
     * crear un paciente con un RUN que ya existe. Devuelve una respuesta con el
     * error
     * y un mensaje descriptivo.
     * 
     * @param ex      La excepción PacienteAlreadyExistsException lanzada.
     * @param request La solicitud HTTP que causó la excepción.
     * @return Una respuesta con el error y un mensaje descriptivo.
     */
    @ExceptionHandler(PacienteAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handlePacienteAlreadyExistsException(PacienteAlreadyExistsException ex,
            HttpServletRequest request) {
        log.error("Paciente ya existe: {}", ex.getMessage());
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.name())
                .message("Paciente ya existe")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Maneja la excepción MethodArgumentNotValidException lanzada cuando la
     * validación
     * de los datos de entrada falla. Devuelve una respuesta con el error y un
     * mensaje descriptivo. El mensaje de error se obtiene de los errores de
     * validación
     * y se incluye en la lista de errores de la respuesta.
     * 
     * @param ex      La excepción MethodArgumentNotValidException lanzada.
     * @param request La solicitud HTTP que causó la excepción.
     * @return Una respuesta con el error y un mensaje descriptivo.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("Error de validación: {}", ex.getMessage());
        // Enumera los errores de validación y los convierte en una lista de mensajes de
        // error
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .toList();
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message("Error de validación")
                .path(request.getRequestURI())
                .errors(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}