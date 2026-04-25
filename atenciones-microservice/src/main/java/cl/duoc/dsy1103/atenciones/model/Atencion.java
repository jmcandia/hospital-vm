package cl.duoc.dsy1103.atenciones.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una atención médica. Esta clase se utiliza para mapear
 * la información de una atención médica en la base de datos. La clase tiene los
 * atributos necesarios para representar la información de una atención médica,
 * como la fecha de la atención, el costo, un comentario y el ID del paciente
 * asociado a la atención. La clase también tiene las anotaciones de JPA
 * necesarias para mapearla a una tabla en la base de datos.
 */
@Entity
@Table(name = "atenciones", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "fecha_atencion", "paciente_id" })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_atencion", nullable = false)
    private LocalDateTime fechaAtencion;

    @Column(name = "costo", nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(name = "comentario", nullable = true, length = 255)
    private String comentario;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;
}
