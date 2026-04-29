package cl.duoc.dsy1103.auth.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cl.duoc.dsy1103.auth.repository.UsuarioRepository;
import cl.duoc.dsy1103.auth.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filter.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (jwtService.isTokenValid(token)) {
            Claims claims = jwtService.extractClaims(token);

            // Valida que el usuario exista
            String nombre = claims.get("name").toString();
            if (!repository.existsByNombre(nombre)) {
                log.warn("Token válido para usuario que no existe: {}", claims.get("name"));
                filter.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), null, List.of());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Token válido para usuario: {}", claims.getSubject());
        } else {
            log.warn("Token inválido en solicitud a: {}", request.getRequestURI());
        }

        filter.doFilter(request, response);
    }
}
