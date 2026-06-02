package cl.SalmonesAustral.Sanitario.dto;

import jakarta.validation.constraints.NotBlank;


/**
 * DTO para crear un nuevo libro (POST) No incluye ID porque se genera automáticamente
 */
public record CreateSanitarioRequest(

    int jaulaId,
    @NotBlank(message = "Enfermedad no puede ser vacía") String enfermedad,
    @NotBlank(message = "Medicamento no puede ser vacío") String medicamento,
    @NotBlank(message = "Dosis no puede ser vacía") double dosis,
    int duracionDias,
    int diasResguardo,
    String observaciones
) {
}
