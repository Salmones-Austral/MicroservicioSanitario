package cl.SalmonesAustral.Sanitario.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoreoAResponse {
   
    private Integer id;
    private Integer jaulaId;
    private Integer usuarioId;
    private Double temperatura;
    private Double oxigenoDisuelto;
    private Double salinidad;
    private Boolean bloomAlgas;
    private LocalDateTime fechaRegistro;

    //public MonitoreoAResponse() {}

    //getter y setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id=id;
    }
    public Integer getJaulaId() {
        return jaulaId;
    }
    public void setJaulaId(Integer jaulaId) {
        this.jaulaId=jaulaId;
    }
     public Integer getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId=usuarioId;
    }
    public Double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(Double temperatura) {
        this.temperatura=temperatura;
    }
    public Double getOxigenoDisuelto() {
        return oxigenoDisuelto;
    }
    public void setOxigenoDisuelto(Double oxigenoDisuelto) {
        this.oxigenoDisuelto=oxigenoDisuelto;
    }
    public Double getSalinidad() {
        return salinidad;
    }
    public void setSalinidad(Double salinidad) {
        this.salinidad=salinidad;
    }
    public Boolean getBloomAlgas() {
        return bloomAlgas;
    }
    public void setBloomAlgas(Boolean bloomAlgas) {
        this.bloomAlgas=bloomAlgas;
    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro=fechaRegistro;
    }
   
}
