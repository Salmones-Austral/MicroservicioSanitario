package cl.SalmonesAustral.Sanitario.service;

import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.repository.SanitarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;



@Service
public class SanitarioService {

    
    private final SanitarioRepository repository;

    public SanitarioService(SanitarioRepository repository) {
        this.repository=repository;
    }

    public Sanitario crearTratamiento(Sanitario t) {
        t.setEstado("ACTIVO"); // ahora existe
        t.setFechaInicio(LocalDate.now());

        if (t.getDiasResguardo() > 0) {
            t.setBloqueaCosecha(true); // ahora existe
        }

        return repository.save(t);
    }

    public Sanitario finalizarTratamiento(Integer id) {

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

    public Sanitario obtenerPorId(Integer id) {
    return repository.findById(id).orElse(null);
}

    public List<Sanitario> porJaula(Integer jaulaId) {
    return repository.findByJaulaId(jaulaId);
}

    public void eliminar(Integer id) {
    repository.deleteById(id);
}

    public boolean puedeCosechar(Integer jaulaId) {
    List<Sanitario> lista = repository.findByJaulaId(jaulaId);
    if(lista.isEmpty()){
        return true;
    }
    LocalDate hoy=LocalDate.now();
    for (Sanitario t : lista) {
        if(t.isBloqueaCosecha()) {
            return false;
        }
        if(t.getFechaInicio()!=null) {
            int diasTotalesTratamiento=t.getDuracionDias() + t.getDiasResguardo();
            LocalDate fechaLiberacionSanitario = t.getFechaInicio().plusDays(diasTotalesTratamiento);

            if(hoy.isBefore(fechaLiberacionSanitario)) {
                return false;
            }
        }
    }
    return true;

}
}