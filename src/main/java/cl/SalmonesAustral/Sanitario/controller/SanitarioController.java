package cl.SalmonesAustral.Sanitario.controller;

import cl.SalmonesAustral.Sanitario.dto.CreateSanitarioRequest;
import cl.SalmonesAustral.Sanitario.dto.UpdateSanitarioRequest;
import cl.SalmonesAustral.Sanitario.mapper.SanitarioMapper;
import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.service.SanitarioService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sanitario")
public class SanitarioController {

    
    private final SanitarioService service;

    public SanitarioController(SanitarioService service) {
        this.service=service;
    }

    //LISTAR TODOS LOS TRATAMIENTOS
    @GetMapping
    public List<Sanitario> listar() {
        return service.listar();
    }

    //OBTENER POR ID
    @GetMapping("/{id}")
    public Sanitario obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    //POR JAULA
    @GetMapping("/jaula/{jaulaId}")
    public List<Sanitario> porJaula(@PathVariable Integer jaulaId) {
        return service.porJaula(jaulaId);
    }

    //  CREAR TRATAMIENTO (VETERINARIA)
    @PostMapping
    public Sanitario crear(@Valid @RequestBody CreateSanitarioRequest request) {
        Sanitario entidad=SanitarioMapper.toModel(request);
        return service.crearTratamiento(entidad);
    }
    @PutMapping("/{id}")
    public Sanitario actualizar(@PathVariable Integer id, @Valid @RequestBody UpdateSanitarioRequest request) {
        Sanitario entidad=SanitarioMapper.toModel(id, request);
        return service.crearTratamiento(entidad);
    }

    // FINALIZAR TRATAMIENTO
    @PutMapping("/finalizar/{id}")
    public Sanitario finalizar(@PathVariable Integer id) {
        return service.finalizarTratamiento(id);
    }

    // VALIDAR SI SE PUEDE COSECHAR
    @GetMapping("/puede-cosechar/{jaulaId}")
    public boolean puedeCosechar(@PathVariable Integer jaulaId) {
        return service.puedeCosechar(jaulaId);
    }

    // ELIMINAR (opcional)
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "Tratamiento eliminado";
    }
}
