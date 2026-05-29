package cl.SalmonesAustral.Sanitario.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cl.SalmonesAustral.Sanitario.modelo.Sanitario;

@Repository
public interface SanitarioRepository extends JpaRepository<Sanitario, Integer> {
    //buscar por jaula
    List<Sanitario>findByJaulaId(int jaulaId);
    //buscar por enfermedad
    List<Sanitario>findByEnfermedad(String enfermedad);
    //buscar por estado
    List<Sanitario>findByEstado(String estado);

    //custom query para total de diagnosticos
    @Query("SELECT COUNT(s) FROM Sanitario s")
    int totalDiagnosticos();
    //custom query para buscar por jaula y estado
    @Query("SELECT s FROM Sanitario s WHERE s.jaulaId=:jaulaId AND s.estado = :estado")
    List<Sanitario> selectPorJaulaYEstado(int jaulaId, String estado);
    // //custom query para buscar por enfermedad y jaula
    @Query("SELECT s FROM Sanitario s WHERE s.enfermedad=:enfermedad AND s.jaulaId = :jaulaId")
    List<Sanitario> selectPorEnfermedadYJaula(String enfermedad, int jaulaId);


    
} 
