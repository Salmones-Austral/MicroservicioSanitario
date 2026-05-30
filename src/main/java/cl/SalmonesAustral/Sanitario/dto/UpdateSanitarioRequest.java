package cl.SalmonesAustral.Sanitario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
/**
 * DTO para actualizar un libro existente (PUT) Incluye ID para identificar qué libro actualizar
 */

public class UpdateSanitarioRequest {

    private Long id;
    private int jaulaId;
    @NotBlank(message = "Enfermedad no puede ser vacía")
    private String enfermedad;
    @NotBlank(message = "Medicamento no puede ser vacío")
    private String medicamento;
    @PositiveOrZero(message = "Dosis no puede ser negativa")
    private double dosis;
    private int duracionDias;
    private int diasResguardo;
    private String observaciones;

    public UpdateSanitarioRequest() {}

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJaulaId() {
        return jaulaId;
    }

    public void setJaulaId(int jaulaId) {
        this.jaulaId = jaulaId;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public double getDosis() {
        return dosis;
    }

    public void setDosis(double dosis) {
        this.dosis = dosis;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public int getDiasResguardo() {
        return diasResguardo;
    }

    public void setDiasResguardo(int diasResguardo) {
        this.diasResguardo = diasResguardo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
