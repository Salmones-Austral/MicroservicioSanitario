package cl.SalmonesAustral.Sanitario.controller;

import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.service.SanitarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sanitario")
public class SanitarioController {

    @Autowired
    private SanitarioService service;

    //LISTAR TODOS LOS TRATAMIENTOS
    @GetMapping
    public List<Sanitario> listar() {
        return service.listar();
    }

    //OBTENER POR ID
    @GetMapping("/{id}")
    public Sanitario obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    //POR JAULA
    @GetMapping("/jaula/{jaulaId}")
    public List<Sanitario> porJaula(@PathVariable int jaulaId) {
        return service.porJaula(jaulaId);
    }

    //  CREAR TRATAMIENTO (VETERINARIA)
    @PostMapping
    public Sanitario crear(@RequestBody Sanitario t) {
        return service.crearTratamiento(t);
    }

    // FINALIZAR TRATAMIENTO
    @PutMapping("/finalizar/{id}")
    public Sanitario finalizar(@PathVariable Long id) {
        return service.finalizarTratamiento(id);
    }

    // VALIDAR SI SE PUEDE COSECHAR
    @GetMapping("/puede-cosechar/{jaulaId}")
    public boolean puedeCosechar(@PathVariable int jaulaId) {
        return service.puedeCosechar(jaulaId);
    }

    // ELIMINAR (opcional)
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "Tratamiento eliminado";
    }
}
