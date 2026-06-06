package cl.SalmonesAustral.Sanitario.mapper;


import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.dto.CreateSanitarioRequest;
import cl.SalmonesAustral.Sanitario.dto.UpdateSanitarioRequest;

public class SanitarioMapper {

    /**
     * Convierte CreateTratamientoRequest a TratamientoSanitario (POST)
     * El ID se genera automáticamente
     */
    public static Sanitario toModel(CreateSanitarioRequest request) {

        return new Sanitario(
        null,
        request.jaulaId(),
        request.enfermedad(),
        request.medicamento(),
        request.dosis(),
        request.duracionDias(),
        request.diasResguardo(),
        request.fechaInicio(),
        request.estado(),
        request.bloqueaCosecha(),
        request.observaciones()
        );
    }

    

    /**
     * Convierte UpdateSanitarioRequest a Sanitario (PUT)
     * El ID viene del path
     */
    public static Sanitario toModel(Integer id, UpdateSanitarioRequest request) {
        return new Sanitario(
        id,
        request.jaulaId(),
        request.enfermedad(),
        request.medicamento(),
        request.dosis(),
        request.duracionDias(),
        request.diasResguardo(),
        request.fechaInicio(),
        request.estado(),
        request.bloqueaCosecha(),
        request.observaciones()
        );    
    }
}
