package cl.duoc.dsy1103.atenciones.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta de una atención médica. Esta clase se
 * utiliza para enviar la información de una atención médica a través de una
 * respuesta HTTP. La clase tiene los atributos necesarios para representar la
 * información de una atención médica, como la fecha de la atención, el costo,
 * un comentario y el paciente asociado a la atención. La clase también tiene
 * las anotaciones de Lombok necesarias para generar automáticamente los métodos
 * getters, setters, constructores y el patrón builder.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtencionResponse {
    private Long id;
    private LocalDateTime fechaAtencion;
    private BigDecimal costo;
    private String comentario;
}
