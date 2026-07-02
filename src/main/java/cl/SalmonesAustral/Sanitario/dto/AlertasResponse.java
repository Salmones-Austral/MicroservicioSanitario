package cl.SalmonesAustral.Sanitario.dto;

import java.time.LocalDateTime;

public class AlertasResponse {
    
    private Long id;
    private Long mortalidadId;
    private int jaulaId;
    private String mensaje;
    // BAJO - MEDIO - ALTO - CRITICO
    private String nivel;
    private LocalDateTime fecha;
    // ACTIVA - RESUELTA
    private String estado;
    private double porcentaje;

    public AlertasResponse() {}


    //getter y setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMortalidadId() { return mortalidadId; }
    public void setMortalidadId(Long mortalidadId) { this.mortalidadId = mortalidadId; }
    public int getJaulaId() { return jaulaId; }
    public void setJaulaId(int jaulaId) { this.jaulaId = jaulaId; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
}


