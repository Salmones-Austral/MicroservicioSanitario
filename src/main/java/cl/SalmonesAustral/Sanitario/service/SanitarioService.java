package cl.SalmonesAustral.Sanitario.service;

import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.repository.SanitarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;



@Service
public class SanitarioService {

    @Autowired
    private SanitarioRepository repository;

    public Sanitario crearTratamiento(Sanitario t) {

        t.setEstado("ACTIVO"); // ahora existe
        t.setFechaInicio(LocalDate.now());

        if (t.getDiasResguardo() > 0) {
            t.setBloqueaCosecha(true); // ahora existe
        }

        return repository.save(t);
    }

    public Sanitario finalizarTratamiento(Long id) {

        Sanitario t = repository.findById(id).orElse(null);

        if (t != null) {
            t.setEstado("FINALIZADO");
            t.setBloqueaCosecha(false);
            return repository.save(t);
        }

        return null;
    }

    public List<Sanitario> listar() {
    return repository.findAll();
}

    public Sanitario obtenerPorId(Long id) {
    return repository.findById(id).orElse(null);
}

    public List<Sanitario> porJaula(int jaulaId) {
    return repository.findByJaulaId(jaulaId);
}

    public void eliminar(Long id) {
    repository.deleteById(id);
}

    public boolean puedeCosechar(int jaulaId) {

    List<Sanitario> lista = repository.findByJaulaId(jaulaId);

    // Si existe al menos uno que bloquea cosecha → NO puede cosechar
    return lista.stream()
            .noneMatch(t -> t.isBloqueaCosecha());
}
}