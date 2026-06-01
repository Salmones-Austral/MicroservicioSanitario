package cl.SalmonesAustral.Sanitario.mapper;

import cl.SalmonesAustral.Sanitario.dto.CreateSanitarioRequest;
import cl.SalmonesAustral.Sanitario.dto.UpdateSanitarioRequest;
import cl.SalmonesAustral.Sanitario.modelo.Sanitario;

public class SanitarioMapper {
    //convierte CreateSanitarioRequest a Sanitario(para post)
    //el id se genera automaticamente , se pasa a 0 temporalmente
    public static Sanitario toModel(CreateSanitarioRequest request) {
        return new Sanitario(
            0,request.jaulaId(),request.usuarioId(),request.diagnostico(),
            request.enfermedad(),request.fechaDiagnostico(),request.estado()
        );
    }

    //Convierte UpdateLibroRequest a Libro (para PUT) El ID se obtiene del path parameter
    public static Sanitario toModel(int id, UpdateSanitarioRequest request) {
        return new Sanitario(id,
            request.jaulaId(), request.usuarioId(),request.diagnostico(),
            request.enfermedad(),request.fechaDiagnostico(),request.estado()
        );
    }

}
