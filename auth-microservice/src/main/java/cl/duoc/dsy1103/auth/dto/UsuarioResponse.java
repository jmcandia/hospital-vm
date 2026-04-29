package cl.duoc.dsy1103.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String nombreCompleto;
    private String correo;
    private String telefono;
}
