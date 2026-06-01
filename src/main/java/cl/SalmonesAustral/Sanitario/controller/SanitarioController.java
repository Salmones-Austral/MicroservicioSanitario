package cl.SalmonesAustral.Sanitario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import cl.SalmonesAustral.Sanitario.dto.CreateSanitarioRequest;
import cl.SalmonesAustral.Sanitario.dto.UpdateSanitarioRequest;
import cl.SalmonesAustral.Sanitario.exception.ResourceNotFoundException;
import cl.SalmonesAustral.Sanitario.mapper.SanitarioMapper;
import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.service.SanitarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/sanitario")
public class SanitarioController {
    private final SanitarioService sanitarioService;
    private final WebClient sanitarioWebClient;
    public SanitarioController (SanitarioService sanitarioService, WebClient sanitarWebClient) {
        this.sanitarioService=sanitarioService;
        this.sanitarioWebClient=sanitarWebClient;
    }
//CRUD BASICOOOOO
    @GetMapping
    public ResponseEntity<List<Sanitario>>listarSanitarios() {
        return ResponseEntity.ok(sanitarioService.getSanitarios());
    }
    @PostMapping
    public ResponseEntity<Sanitario>crearSanitario(@Valid @RequestBody CreateSanitarioRequest request) {
        Sanitario nuevo=sanitarioService.saveSanitario(SanitarioMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sanitario>obtenerPorId(@PathVariable int id) {
        Sanitario sanitario=sanitarioService.getSanitarioId(id);
        if(sanitario==null) {
            throw new ResourceNotFoundException ("Dashboard Sanitario no encontrado para id ingresado: " + id);
        }
            return ResponseEntity.ok(sanitario);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Sanitario>actualizarSanitario(
        @PathVariable int id, @Valid @RequestBody UpdateSanitarioRequest request) {
            Sanitario actualizado = sanitarioService.updateSanitario(
                SanitarioMapper.toModel(id, request));
                return ResponseEntity.ok(actualizado);
            
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminarSanitario(@PathVariable int id) {
        sanitarioService.deleteSanitario(id);
        return ResponseEntity.noContent().build();
    }
    //metodos de negocio
    @GetMapping
    ("/total")public ResponseEntity<Integer>totalDianosticos() {
        return ResponseEntity.ok(sanitarioService.totalDiagnosticos());
    }
    @GetMapping
    ("/jaula/{jaulaId}") public ResponseEntity<List<Sanitario>> 
    obtenerPorJaula(@PathVariable int jaulaId) {
        return ResponseEntity.ok(sanitarioService.obtenerPorJaula(jaulaId));
    }
    @GetMapping
    ("/enfermedad/{enfermedad}") public ResponseEntity<List<Sanitario>>
    obtenerPorEnfermedad(@PathVariable String enfermedad) {
        return ResponseEntity.ok(sanitarioService.obtenerPorEnfermedad(enfermedad));
    }
    @GetMapping
    ("/estado/{estado}")public ResponseEntity<List<Sanitario>> 
    obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(sanitarioService.findByEstado(estado));
    }
    @GetMapping
    ("/jaula/{jaulaId}/estado/{estado}")public ResponseEntity<List<Sanitario>>
    obtenerPorJaulaYEstado(@PathVariable int jaulaId, @PathVariable String estado) {
        return ResponseEntity.ok(sanitarioService.findByJaulaYEstado(jaulaId, estado));
    }
    //COMUNICACION CON MICROSERVICIOS
    //consultar jaula desde microservicio jaulas

    @GetMapping
    ("/jaulaInfo")
    public ResponseEntity<Object>consultarJaula(@RequestParam int jaulaId) {
        Object jaula=sanitarioWebClient.get()
        .uri("api/v1/jaulas{id}", jaulaId)
        .retrieve().bodyToMono(Object.class).block();
        return ResponseEntity.ok(jaula);
    }
////NOTIFICAR A ALERTAS CUANDO HAY ENFERMEDAD CRITICA.
    
        
    @GetMapping
    ("/notificarAAlerta")public ResponseEntity<String>notificarAAlerta
    (@RequestParam String mensaje) {
        String respuesta=sanitarioWebClient.get()
        .uri("/api/v1/alertas/recibir?mensaje={mensaje}", mensaje)
        .retrieve().bodyToMono(String.class).block();
        return ResponseEntity.ok(respuesta);
    }

    //RECIBIR MENSAJE DESDE OTRO MICROSERVICIO
    @GetMapping("/recibir")
    public ResponseEntity<String>recibirMensaje
    (@RequestParam String mensaje) {
        System.out.println("Mensaje recibido en Microservicio Sanitario: " +mensaje);
        return ResponseEntity.ok("Microservicio Sanitario recibió: " +mensaje);

    }
}
