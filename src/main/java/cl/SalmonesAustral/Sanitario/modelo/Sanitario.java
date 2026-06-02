package cl.SalmonesAustral.Sanitario.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Sanitario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int jaulaId;

    private String enfermedad;

    private String medicamento;

    private double dosis;

    private int duracionDias;

    private int diasResguardo;

    private LocalDate fechaInicio;

    // ESTE ES EL QUE TE FALTABA
    private String estado;

    // Y ESTE TAMBIÉN
    private boolean bloqueaCosecha;

    private String observaciones;

    public Sanitario() {}

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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    //  IMPORTANTE
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //  IMPORTANTE
    public boolean isBloqueaCosecha() {
        return bloqueaCosecha;
    }

    public void setBloqueaCosecha(boolean bloqueaCosecha) {
        this.bloqueaCosecha = bloqueaCosecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
   
 
}