package cl.SalmonesAustral.Sanitario.service;

import cl.SalmonesAustral.Sanitario.dto.AlertasResponse;
import cl.SalmonesAustral.Sanitario.dto.DashboardVetResponse;
import cl.SalmonesAustral.Sanitario.dto.MonitoreoAResponse;
import cl.SalmonesAustral.Sanitario.dto.MortalidadResponse;
import cl.SalmonesAustral.Sanitario.exception.ResourceNotFoundException;
import cl.SalmonesAustral.Sanitario.exception.ServicioExternoException;
import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.repository.SanitarioRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.util.List;



@Service
public class SanitarioService {

    
    private final SanitarioRepository repository;

    //webclients para conectar cn los otros ms
    private final WebClient alertasWebClient;
    private final WebClient monitoreoAWebClient;
    private final WebClient mortalidadWebClient;

    //constructor con los qualifeir

    public SanitarioService(SanitarioRepository repository,
        @Qualifier("alertasWebClient") WebClient alertasWebClient,
        @Qualifier("monitoreoAWebClient") WebClient monitoreoAWebClient,
        @Qualifier("mortalidadWebClient") WebClient mortalidadWebClient
    ) {
        this.repository=repository;
        this.alertasWebClient=alertasWebClient;
        this.monitoreoAWebClient=monitoreoAWebClient;
        this.mortalidadWebClient=mortalidadWebClient;
    }

    //logica del dashboard vet
    //GET: solo sirve para darle un panorama completo a la veterinaria, de los ms alertas, mortalidad y monitoreo ambiental, que pueda ver la info por jaulaId

    public DashboardVetResponse obtenerDashboardVet(Integer jaulaId) {
        DashboardVetResponse dashboard = new DashboardVetResponse();
        dashboard.setJaulaId(jaulaId);

        try{
            dashboard.setAlertas(consultarAlertas(jaulaId));
        }catch (ResourceNotFoundException | ServicioExternoException e) {
            System.out.println("Error al conectar con ms Alertas: " + e.getMessage());
            //en vez de romper todo el programa, maneja el error de alertas
            dashboard.setAlertas(new AlertasResponse());
        }

        try{
            dashboard.setMonitoreoA(consultarMonitoreoA(jaulaId));
        }catch (Exception e) {
            //si falla monitoreo, el resto de cod sigue
        }
        try{
            dashboard.setMortalidad(consultarMortalidad(jaulaId));
        }catch (Exception e) {
            System.out.println("error en ms mortalidad: " + e.getMessage());
            e.printStackTrace();
            //si falla, es lo mismo 
        }
        return dashboard;
    }
    //metodos privados, traduciendo los errores de WebClient a excepciones propias de este servicio.
    private AlertasResponse consultarAlertas(int jaulaId) {
        try{
            return alertasWebClient.get()
            .uri("/jaula/{id}", jaulaId)
            .retrieve()

            //procesa los corchetes de la lista
            .bodyToFlux(AlertasResponse.class)
            //extrae el 1er objeto {} que este dentro
            .next()
            //resuelve y devuelve el resultado
            .block();
        }catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("No se encontraron alertas para esta jaula: " + jaulaId);
        }catch (WebClientException ex) {
            ex.printStackTrace(); 
    
            throw new ServicioExternoException("No se pudo conectar al servicio de alertas", ex);//
        }
        
        
    }

    private MonitoreoAResponse consultarMonitoreoA(int jaulaId) {
        try{
            MonitoreoAResponse[] respuestas = monitoreoAWebClient.get()
            //return monitoreoAWebClient.get()
            .uri("/jaula/{id}", jaulaId)
            .retrieve()
            .bodyToMono(MonitoreoAResponse[].class)
            .block();
            if (respuestas != null && respuestas.length>0) {
                return respuestas[0];
            }
            return null;
        //}catch (WebClientResponseException.NotFound ex) {
            //throw new ResourceNotFoundException("No se encontraron datos de Monitoreo Ambiental para la jaula: " + jaulaId, ex);
        }catch (WebClientException ex) {
            throw new ServicioExternoException("No se pudo conectar al servicio de Monitoreo Ambiental", ex);
        }
    }

    private MortalidadResponse consultarMortalidad(int jaulaId) {
        try{
            //consulta el endpoint del promedio que devuelve double
             Double promedioMortalidad  = mortalidadWebClient.get()
            .uri("/jaula/{id}/promedio", jaulaId)
            .retrieve()
            .bodyToMono(Double.class)
            .block();
            if (promedioMortalidad != null) {
                MortalidadResponse res = new MortalidadResponse();
                //asgina el promedio al atributo correspondeinte y ajusta "setPorcentaje" o "setpromedio" segun nombre clase
                res.setPorcentaje(promedioMortalidad);
                res.setJaulaId(jaulaId);
                return res;
            }
            return null;

        //}catch (WebClientResponseException.NotFound ex) {
          //  throw new ResourceNotFoundException( "No hay registros de mortalidad para la jaula: " + jaulaId, ex);
        }catch (WebClientException ex) {
            throw new ServicioExternoException("No se pudo conectar al servio de mortalidad", ex);
        }
    }

    public Sanitario crearSanitario(Sanitario t) {
        t.setEstado("ACTIVO"); // ahora existe
        t.setFechaInicio(LocalDate.now());

        if (t.getDiasResguardo() > 0) {
            t.setBloqueaCosecha(true); // ahora existe
        }

        return repository.save(t);
    }


        public List<Sanitario> listar() {
            return repository.findAll();
        }

        public Sanitario obtenerPorId(Integer id) {
            return repository.findById(id).orElse(null);
        }




    public Sanitario actualizar(Sanitario sanitario) {
        Sanitario t = repository.findById(sanitario.getId()).orElse(null);
        if (t != null) {
            t.setEstado("FINALIZADO");
            t.setBloqueaCosecha(false);
            return repository.save(t);
        }
        return null;
    }

     public void eliminar(Integer id) {
        repository.deleteById(id);
    }


    public List<Sanitario> porJaula(Integer jaulaId) {
        return repository.findByJaulaId(jaulaId);
    }


    public boolean puedeCosechar(Integer jaulaId) {
        List<Sanitario> lista = repository.findByJaulaId(jaulaId);
        if(lista == null || lista.isEmpty()){
            return true;
        }
    LocalDate hoy=LocalDate.now();
    for (Sanitario t : lista) {
        if(t.isBloqueaCosecha()) {
            return false;
        }
        if(t.getFechaInicio()!=null) {

            // Si duración o resguardo vienen nulos, les asignamos 0 para que no se interrumpa el proceso
            int duracion = (t.getDuracionDias() != null) ? t.getDuracionDias() : 0;
            int resguardo = (t.getDiasResguardo() != null) ? t.getDiasResguardo() : 0;
            
            int diasTotalesTratamiento = duracion + resguardo;
            LocalDate fechaLiberacionSanitario = t.getFechaInicio().plusDays(diasTotalesTratamiento);

            if(hoy.isBefore(fechaLiberacionSanitario)) {
                return false;
            }
        }
    }
    return true;

}
}