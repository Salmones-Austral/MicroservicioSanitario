package cl.SalmonesAustral.Sanitario.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateSanitarioRequest(
    @NotNull(message="El id de jaula no puede ser nulo" ) int jaulaId,

    @NotNull(message="El id de usuario no puede ser nulo" ) int usuarioId,

    @NotNull(message="El diagnostico no puede estar vacio") String diagnostico,

    @NotBlank(message="La enfermedad debe ser registrada, no puede estar vacia ") String enfermedad,

    @NotNull (message="La fecha del diagnostico es obligatoria") LocalDateTime fechaDiagnostico,

    @NotBlank(message="El estado de jaula no puede estar vacio") String estado
) {


}
