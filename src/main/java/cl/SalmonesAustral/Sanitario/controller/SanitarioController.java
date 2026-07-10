package cl.SalmonesAustral.Sanitario.controller;

import cl.SalmonesAustral.Sanitario.dto.CreateSanitarioRequest;
import cl.SalmonesAustral.Sanitario.dto.DashboardVetResponse;
import cl.SalmonesAustral.Sanitario.dto.UpdateSanitarioRequest;
import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.service.SanitarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sanitario")
@Tag(name= "Sanitario", description=" API para gestion de registros sanitarios y orquestacion del Dashboard ")
public class SanitarioController {

    private final SanitarioService service;

    public SanitarioController(SanitarioService service) {
        this.service=service;
    }

    //1er ENDPOINT: CREAR registro (se usan las anotaciones pedidas para evaluacion 3.)
    @PostMapping
    @Operation(
        summary = "Crear un nuevo registro Sanitario", description = "Guarda un tratamiento medico aplicado a una jaula",
        //truco para que no salga error de duplicidad, documentar el cuerpo de la peticionaqui dentro
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            //anotacion de swagger para documentar el cuerpo de la peticion
        
            description = "Objeto Sanitario con los detalles del control",
            required = true,
            content = @Content(
                mediaType = "aplication/json",
                schema = @Schema(implementation = Sanitario.class),
                examples = @ExampleObject(
                    name = "Ejemplo de control Sanitario",
    
                    //este json aparecera precargado en swagger para la demo en vivo
                    value = "{\n" +
                            "\"jaulaId\": 14,\n" +
                            "\"enfermedad\": \"Caligidosis\",\n" +
                            "\"medicamento\": \"azametifos\",\n" +
                            "\"dosis\": 2.5,\n" +
                            "\"duracionDias\": 7,\n" +
                            "\"diasResguardo\": 45,\n" +
                            "\"fechaInicio\": \"2026-03-01\",\n" +
                            "\"estado\": \"EN_TRATAMIENTO\",\n" +
                            "\"bloqueaCosecha\": \"true\", \n" +
                            "\"observaciones\": \"Aplicar tratamiento\",\n" +
                           
                        "}"
                )
            )
        )
    )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro sanitario creado exitosamente "),
            @ApiResponse(responseCode = "400", description = "Los datos ingresados son inválidos ")
    })
     
    public Sanitario crearRegistro(@RequestBody Sanitario sanitario) {
        return service.crearSanitario(sanitario);
        }

        //PUT: actualizar registro (mismo truco para evitar duplicidad)

        @PutMapping("/{id}")
        @Operation(
            summary = "Actualizar un registro sanitario existente",
            description = "Modifica los datos de un control sanitario en especifico usando su ID en la URL",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos actualizados del registro sanitario",
                required = true,
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Sanitario.class),
                    examples = @ExampleObject(
                        name = "Ejemplo de actualizacion",
                        value = "{\n" +
                                "\"jaulaId\": 14,\n" +
                                "\"enfermedad\": \"Caligidosis\",\n" +
                                "\"medicamento\": \"azametifos\",\n" +
                                "\"dosis\": 2.5,\n" +
                                "\"duracionDias\": 7,\n" +
                                "\"diasResguardo\": 45,\n" +
                                "\"fechaInicio\": \"2026-03-01\",\n" +
                                "\"estado\": \"FINALIZADO\",\n" +
                                "\"bloqueaCosecha\": \"false\", \n" +
                                "\"observaciones\": \"Tratamiento terminado. Resguardo cumplido\",\n" +                      
                                "}"

                    )
                )
            )
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro sanitario actualizado con exito "),
            @ApiResponse(responseCode = "404", description = "Ese registro no existe ")
    })

        public Sanitario actualizar(@PathVariable Integer id, @Valid @RequestBody Sanitario sanitario) {
            sanitario.setId(id);
            return service.actualizar(sanitario);
    }


        //2do endpoint: GET/ dashboard (el backend for frontend que consume otros microservicios)
        @GetMapping("/dashboard/{jaulaId}")
        @Operation(
            summary = "Obtener Dashboard de la Veterinaria (BFF)",
            description = "Consume datos de Alertas, Monitoreo Ambiental y Mortalidad para una jaula especifica."
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dashboard generado correctamente"),
            @ApiResponse(responseCode = "404", description = "Datos no encontrados")
            
        })
        public DashboardVetResponse obtenerDashboard(@PathVariable Integer jaulaId) {
            return service.obtenerDashboardVet(jaulaId);
        }


    //obtener todos. GET
    @GetMapping
    @Operation(summary = "Obtener todos los registros sanitarios")
    @ApiResponse(responseCode = "200", description = "Lista de registros obtenida correctamente")
    public List<Sanitario> obtenerTodos() {
        return service.listar();
    }

    //OBTENER POR ID. GET
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un registro por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registro encontrado"),
        @ApiResponse(responseCode = "404", description = "El registro no existe")
    })
    public Sanitario obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }


    //GET. obtener registros POR JAULA
    @GetMapping("/jaula/{jaulaId}")
    @Operation(
        summary = "Obtener un historial sanitario por jaula",
        description = "retorna una lista con los tratamientos de una jaula"
    )
    public List<Sanitario> porJaula(@PathVariable Integer jaulaId) {
        return service.porJaula(jaulaId);
    }



    // GET: VALIDAR SI SE PUEDE COSECHAR
    @GetMapping("/puede-cosechar/{jaulaId}")
    @Operation(
        summary = "Validar si una jaula se puede cosechar",
        description = "verifica si la jaula tiene algun tratamiento activo o resguardo (retorna tru o false)"
    )
    @ApiResponse(responseCode = "200", description = "Validacion realiazada con exito")

    public boolean puedeCosechar(@PathVariable Integer jaulaId) {
        return service.puedeCosechar(jaulaId);
    }

    // ELIMINAR registro(opcional), delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro sanitario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registro eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "El registro a eliminar no existe")
    })
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
