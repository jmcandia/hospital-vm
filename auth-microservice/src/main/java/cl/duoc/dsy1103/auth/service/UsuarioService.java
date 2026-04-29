package cl.duoc.dsy1103.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.dsy1103.auth.dto.UsuarioResponse;
import cl.duoc.dsy1103.auth.mapper.UsuarioMapper;
import cl.duoc.dsy1103.auth.model.Usuario;
import cl.duoc.dsy1103.auth.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<UsuarioResponse> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioMapper::toResponse).toList();
    }

    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse obtenerUsuarioPorNombre(String nombre) {
        Usuario usuario = usuarioRepository.findByNombre(nombre).get();
        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse crear(Usuario usuario) {
        if (usuarioRepository.existsByNombre(usuario.getNombre())) {
            throw new IllegalArgumentException("Usuario ya existe");
        }
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuario);
    }
}
