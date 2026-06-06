package cl.SalmonesAustral.Sanitario.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


/**
 * DTO para crear un nuevo libro (POST) No incluye ID porque se genera automáticamente
 */
public record CreateSanitarioRequest(

    @NotNull(message = "El ID de la jaula es obligatorio")
    @Positive(message = "El ID de la jaula debe ser un numero positivo")
    Integer jaulaId,


    @NotBlank(message = "Enfermedad no puede estar vacía") 
    String enfermedad,

    @NotBlank(message = "Medicamento no puede estar vacío") 
    String medicamento,
    
    @NotNull(message = "La dosis es obligatoria")
    @Positive(message = "La dosis no puede ser un valor negativo")
    Double dosis,

    @NotNull(message = "La duracion en dias es obligatoria")
    Integer duracionDias,

    @NotNull(message = "Los dias de resguardo son obligatorios")
    int diasResguardo,

    @NotNull(message = "La fecha de inicio del tratamiento es obligatoria")
    LocalDate fechaInicio,

    @NotBlank(message = "El estado del tratamiento es obligatorio")
    String estado,

    boolean bloqueaCosecha,

    String observaciones
) {
}
