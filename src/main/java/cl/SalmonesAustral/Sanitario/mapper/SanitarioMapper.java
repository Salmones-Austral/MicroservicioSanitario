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

        Sanitario t = new Sanitario();

        t.setJaulaId(request.jaulaId());
        t.setEnfermedad(request.enfermedad());
        t.setMedicamento(request.medicamento());
        t.setDosis(request.dosis());
        t.setDuracionDias(request.duracionDias());
        t.setDiasResguardo(request.diasResguardo());
        t.setObservaciones(request.observaciones());

        // NO seteamos estado ni fecha → lo hace el SERVICE

        return t;
    }

    /**
     * Convierte UpdateSanitarioRequest a Sanitario (PUT)
     * El ID viene del path
     */
    public static Sanitario toModel(Long id, UpdateSanitarioRequest request) {

        // Para update, solo seteamos los campos que se pueden modificar (no ID, estado ni fecha)
        Sanitario t = new Sanitario();

        t.setId(id);
        t.setJaulaId(request.getJaulaId());
        t.setEnfermedad(request.getEnfermedad());
        t.setMedicamento(request.getMedicamento());
        t.setDosis(request.getDosis());
        t.setDuracionDias(request.getDuracionDias());
        t.setDiasResguardo(request.getDiasResguardo());
        t.setObservaciones(request.getObservaciones());

        return t;
    }
}
