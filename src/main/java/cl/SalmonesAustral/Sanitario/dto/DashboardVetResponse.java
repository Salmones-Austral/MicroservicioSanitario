package cl.SalmonesAustral.Sanitario.dto;

public class DashboardVetResponse {
    private Integer jaulaId;
    private AlertasResponse alertas;
    private MonitoreoAResponse monitoreoA;
    private MortalidadResponse mortalidad;

    public DashboardVetResponse() {}

    public Integer getJaulaId() {
        return jaulaId;
    }
    public void setJaulaId(Integer jaulaId) {
        this.jaulaId=jaulaId;
    }

    public AlertasResponse getAlertas() {
        return alertas;
    }
    public void setAlertas(AlertasResponse alertas) {
        this.alertas = alertas;
    }
    public MonitoreoAResponse getMonitoreoA() {
        return monitoreoA;
    }
    public void setMonitoreoA(MonitoreoAResponse monitoreoA) {
        this.monitoreoA = monitoreoA;
    }
    public MortalidadResponse getMortalidad() {
        return mortalidad;
    }
    public void setMortalidad(MortalidadResponse mortalidad) {
        this.mortalidad = mortalidad;
    }


}
