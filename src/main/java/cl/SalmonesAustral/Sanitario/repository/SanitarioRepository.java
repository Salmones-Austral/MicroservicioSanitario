package cl.SalmonesAustral.Sanitario.repository;

import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface SanitarioRepository extends JpaRepository<Sanitario, Long> {

    List<Sanitario> findByJaulaId(int jaulaId);

    List<Sanitario> findByEstado(String estado);

    //  tratamientos activos (importante para bloqueo)
    @Query("SELECT t FROM Sanitario t WHERE t.estado = 'ACTIVO'")
    List<Sanitario> tratamientosActivos();
    

}
    

