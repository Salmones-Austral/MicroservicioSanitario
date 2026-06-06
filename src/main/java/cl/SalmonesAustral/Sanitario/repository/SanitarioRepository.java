package cl.SalmonesAustral.Sanitario.repository;

import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface SanitarioRepository extends JpaRepository<Sanitario, Integer> {

    List<Sanitario> findByJaulaId(Integer jaulaId);

    List<Sanitario> findByEstado(String estado);

    //  tratamientos activos (importante para bloqueo)
    
    List<Sanitario> findByEstadoIgnoreCase(String estado);
    

}
    

