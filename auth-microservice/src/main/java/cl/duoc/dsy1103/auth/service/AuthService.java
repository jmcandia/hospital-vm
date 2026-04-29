package cl.duoc.dsy1103.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.duoc.dsy1103.auth.dto.AuthResponse;
import cl.duoc.dsy1103.auth.dto.LoginRequest;
import cl.duoc.dsy1103.auth.model.Usuario;
import cl.duoc.dsy1103.auth.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public AuthResponse login(LoginRequest request) {
        // 1. Valida que el usuario exista
        if (!usuarioRepository.existsByNombre(request.getNombre())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }
        // 2. Buscamos al usuario por nombre
        Usuario usuario = usuarioRepository.findByNombre(request.getNombre()).get();
        // 3. Validamos la contraseña
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            log.warn("Contraseña incorrecta para el usuario: {}", usuario.getNombre());
            throw new BadCredentialsException("Credenciales inválidas");
        }
        // 4. Generamos el token para el usuario
        String token = jwtService.generateToken(usuario);
        // 5. Retornamos el token
        return AuthResponse.builder()
                .token(token)
                .nombre(usuario.getNombre())
                .expiresIn(jwtExpiration)
                .build();
    }
}
