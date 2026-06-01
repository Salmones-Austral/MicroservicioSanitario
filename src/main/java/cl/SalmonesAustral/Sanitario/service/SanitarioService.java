package cl.SalmonesAustral.Sanitario.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.repository.SanitarioRepository;

@Service
public class SanitarioService {
    @Autowired
    private SanitarioRepository sanitarioRepository;

    //get todos
    public List<Sanitario>getSanitarios(){
        return sanitarioRepository.findAll();
    }
    //post guardar
    public Sanitario saveSanitario(Sanitario sanitario) {
        return sanitarioRepository.save(sanitario);
    }
    //get por id
    public Sanitario getSanitarioId(int id) {
        return sanitarioRepository.findById(id).orElse(null);
    }
    //put actualizar
    public Sanitario updateSanitario(Sanitario sanitario) {
        return sanitarioRepository.save(sanitario);
    }
    //delete
    public void deleteSanitario(int id) {
        sanitarioRepository.deleteById(id);
    }
    //la accion la hace el service
    public int totalDiagnosticos() {
        return(int) sanitarioRepository.count();
    }
    //get por jaula
    public List<Sanitario>obtenerPorJaula(int jaulaId) {
        return sanitarioRepository.findByJaulaId(jaulaId);
    }
    //get por enfermedad
    public List<Sanitario>obtenerPorEnfermedad(String enfermedad) {
        return sanitarioRepository.findByEnfermedad(enfermedad);
    }
    //get por estado
    public List<Sanitario>findByEstado(String estado) {
        return sanitarioRepository.findByEstado(estado);
    }
    //get por jaula y estado
    public List<Sanitario>findByJaulaYEstado(int jaulaId, String estado) {
        return sanitarioRepository.selectPorJaulaYEstado(jaulaId, estado);
    }
    //get por enfermedad y jaula
    public List<Sanitario>findByEnfermedadYJaula(String enfermedad, int jaulaId) {
        return sanitarioRepository.selectPorEnfermedadYJaula(enfermedad, jaulaId);
    }
}
