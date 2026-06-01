package cl.SalmonesAustral.Sanitario.repository;

import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface SanitarioRepository extends JpaRepository<Sanitario, Integer> {

    List<Sanitario> findByJaulaId(int jaulaId);
    List<Sanitario> findByEnfermedad(String enfermedad);

    List<Sanitario> findByEstado(String estado);

    //  tratamientos activos (importante para bloqueo)
    @Query("SELECT COUNT(s) FROM Sanitario s")
    int totalDiagnosticos();

    @Query("SELECT s FROM Sanitario WHERE s.jaulaId= :jaulaId AND s.estado= :estado")
    List<Sanitario>selectPorJaulaYEstado(int jaulaId, String estado);

    @Query("SELECT s FROM Sanitario s WHERE s.enfermedad =:enfermedad AND s.jaulaId= :jaulaId")
    List<Sanitario> selectPorEnfermedadYJaula(String enfermedad, int jaulaId);
    

}
    

