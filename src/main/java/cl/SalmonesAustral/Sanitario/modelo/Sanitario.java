package cl.SalmonesAustral.Sanitario.modelo;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sanitario")
public class Sanitario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="jaula_id", nullable=false)
    private int jaulaId;

    @Column(name="usuario_id", nullable=false)
    private int usuarioId;

    @Column(name="diagnostico", nullable=false)
    private String diagnostico;

    @Column(name="enfermedad", nullable=false)
    private String enfermedad;

    @Column(name="fecha_diagnostico", nullable=false)
    private LocalDateTime fechaDiagnostico;

    @Column(name="estado", nullable=false)
    private String estado;

}
