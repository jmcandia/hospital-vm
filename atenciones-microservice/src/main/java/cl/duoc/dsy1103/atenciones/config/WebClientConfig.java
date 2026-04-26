package cl.duoc.dsy1103.atenciones.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuración de WebClient para comunicarse con otros microservicios.
 * Permite configurar la URL base y otras propiedades necesarias para las
 * llamadas HTTP.
 */
@Configuration
public class WebClientConfig {

    @Value("${services.pacientes.baseUrl}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
