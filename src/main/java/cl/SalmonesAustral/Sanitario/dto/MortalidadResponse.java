package cl.SalmonesAustral.Sanitario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MortalidadResponse {
   
    private Integer id; 
    private int jaulaId;
    private double porcentaje;
    private int dias;

    //public MortalidadResponse() {}

    /*// GETTERS Y SETTERS
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public int getJaulaId() { return jaulaId; }
    public void setJaulaId(int jaulaId) { this.jaulaId = jaulaId; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
    public int getDias() { return dias; }
    public void setDias(int dias) { this.dias = dias; }
*/
}
