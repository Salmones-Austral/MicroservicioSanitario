package cl.SalmonesAustral.Sanitario.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity

@Table(name="tabla_sanitario")
public class Sanitario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="id_san")
    private int idSan;

    @Column
    (name="jaula_id", nullable=false) private int jaulaId;

    @Column
    (name="usuario_id", nullable=false) private int usuarioId;

    @Column
    (name="diagnostico", nullable=false, length=200) private String diagnostico;

    @Column
    (name="enfermedad", nullable=false, length=100) private String enfermedad;

    @Column
    (name="fecha_diagnostico", nullable=false) private LocalDateTime fechaDiagnostico;
    // estados= activo: recien detectado sin tratamiento aun, en tratamiento: la dra molina ya puso un plan de tratamiento, 
    // resuelto: la enfermedad se trato, critico: brote grave c/riesgo de sacrificar jaula
    @Column
    (name="estado", nullable=false, length=20) private String estado;


    //Constructor sin argumentos
    public Sanitario() {}
    //constructor completo
    public Sanitario(int idSan, int jaulaId, int usuarioId, String diagnostico, String enfermedad,
        LocalDateTime fechaDiagnostico, String estado){
            this.idSan=idSan;
            this.jaulaId=jaulaId;
            this.usuarioId=usuarioId;
            this.diagnostico=diagnostico;
            this.enfermedad=enfermedad;
            this.fechaDiagnostico=fechaDiagnostico;
            this.estado=estado;
        }
    //GETTERS AND SETTERS
    public int getIdSan() {
        return idSan;}
    public void setIdSan(int idSan) {
        this.idSan=idSan;}
    public int getJaulaId() {
        return jaulaId;}
    public void setJaulaId(int jaulaId) {
        this.jaulaId=jaulaId;}
    public int getUsuarioId() {
        return usuarioId;}
    public void setUsuarioId(int usuarioId) {
        this.usuarioId=usuarioId;} 
    public String getDiagnostico() {
        return diagnostico;}
    public void setDiagnostico(String diagnostico) {
        this.diagnostico=diagnostico;}
    public String getEnfermedad() {
        return enfermedad;}
    public void setEnfermedad(String enfermedad) {
        this.enfermedad=enfermedad;}
    public LocalDateTime getFechaDiagnostico() {
        return fechaDiagnostico;}
    public void setFechaDiagnostico(LocalDateTime fechaDiagnostico) {
        this.fechaDiagnostico=fechaDiagnostico;}
    public String getEstado() {
        return estado;}
    public void setEstado(String estado) {
        this.estado=estado;}
    }