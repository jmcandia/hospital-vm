package cl.duoc.dsy1103.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.dsy1103.auth.dto.UsuarioResponse;
import cl.duoc.dsy1103.auth.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/v1/usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponse> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponse obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public UsuarioResponse obtenerUsuarioPorNombre(@PathVariable String nombre) {
        return usuarioService.obtenerUsuarioPorNombre(nombre);
    }
}
