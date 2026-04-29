package cl.duoc.dsy1103.auth.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.dsy1103.auth.dto.UsuarioResponse;
import cl.duoc.dsy1103.auth.model.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .nombreCompleto(usuario.getNombreCompleto())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .build();
    }
}
