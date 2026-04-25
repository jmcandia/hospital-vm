package cl.duoc.dsy1103.atenciones.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.dsy1103.atenciones.dto.AtencionRequest;
import cl.duoc.dsy1103.atenciones.dto.AtencionResponse;
import cl.duoc.dsy1103.atenciones.model.Atencion;

/**
 * Clase que proporciona métodos para mapear entre objetos de tipo Atencion,
 * AtencionRequest y AtencionResponse.
 */
@Component
public class AtencionMapper {

    /**
     * Convierte un objeto AtencionRequest a un objeto Atencion.
     *
     * @param request El objeto AtencionRequest que contiene los datos de la
     *                atención.
     * @return Un objeto Atencion con los datos mapeados desde el request.
     */
    public Atencion fromRequest(AtencionRequest request) {
        return Atencion.builder()
                .fechaAtencion(request.getFechaAtencion())
                .costo(request.getCosto())
                .comentario(request.getComentario())
                .build();
    }

    /**
     * Convierte un objeto Atencion a un objeto AtencionResponse.
     *
     * @param atencion El objeto Atencion que contiene los datos de la atención.
     * @return Un objeto AtencionResponse con los datos mapeados desde la
     *         atención.
     */
    public AtencionResponse toResponse(Atencion atencion) {
        return AtencionResponse.builder()
                .id(atencion.getId())
                .fechaAtencion(atencion.getFechaAtencion())
                .costo(atencion.getCosto())
                .comentario(atencion.getComentario())
                .build();
    }
}
