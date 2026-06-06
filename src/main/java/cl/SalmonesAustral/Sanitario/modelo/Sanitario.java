package cl.SalmonesAustral.Sanitario.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="tabla_sanitario")
public class Sanitario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "jaula_id", nullable = false)
    private Integer jaulaId;
    @Column(name = "enfermedad", length = 100)
    private String enfermedad;
    @Column(name = "medicamento", length = 100)
    private String medicamento;
    @Column(name = "dosis")
    private Double dosis;
    @Column(name = "duracion_dias")
    private Integer duracionDias;
    @Column(name = "dias_resguardo")
    private Integer diasResguardo;
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
    @Column(name = "estado", length = 50)
    private String estado;
    @Column(name = "bloquea_cosecha")
    private boolean bloqueaCosecha;
    @Column(name = "observaciones", length = 500)
    private String observaciones;

    public Sanitario() {}

    public Sanitario(Integer id, Integer jaulaId, String enfermedad, String medicamento, Double dosis, Integer duracionDias,
        Integer diasResguardo, LocalDate fechaInicio, String estado, boolean bloqueaCosecha, String observaciones) {
            this.id=id;
            this.jaulaId=jaulaId;
            this.enfermedad=enfermedad;
            this.medicamento=medicamento;
            this.dosis=dosis;
            this.duracionDias=duracionDias;
            this.diasResguardo=diasResguardo;
            this.fechaInicio=fechaInicio;
            this.estado=estado;
            this.bloqueaCosecha=bloqueaCosecha;
            this.observaciones=observaciones;
        }

    // GETTERS Y SETTERS

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getJaulaId() {
        return jaulaId;
    }
    public void setJaulaId(Integer jaulaId) {
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
    public Double getDosis() {
        return dosis;
    }
    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }
    public Integer getDuracionDias() {
        return duracionDias;
    }
    public void setDuracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
    }
    public Integer getDiasResguardo() {
        return diasResguardo;
    }
    public void setDiasResguardo(Integer diasResguardo) {
        this.diasResguardo = diasResguardo;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
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