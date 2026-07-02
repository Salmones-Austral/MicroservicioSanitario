package cl.SalmonesAustral.Sanitario.service;

import cl.SalmonesAustral.Sanitario.dto.AlertasResponse;
import cl.SalmonesAustral.Sanitario.dto.DashboardVetResponse;
import cl.SalmonesAustral.Sanitario.dto.MonitoreoAResponse;
import cl.SalmonesAustral.Sanitario.dto.MortalidadResponse;
import cl.SalmonesAustral.Sanitario.exception.ResourceNotFoundException;
import cl.SalmonesAustral.Sanitario.exception.ServicioExternoException;
import cl.SalmonesAustral.Sanitario.modelo.Sanitario;
import cl.SalmonesAustral.Sanitario.repository.SanitarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        @Qualifier("mortalidadWebClient") WebClient mortalidadWebClient) {
        this.repository=repository;
        this.alertasWebClient=alertasWebClient;
        this.monitoreoAWebClient=monitoreoAWebClient;
        this.mortalidadWebClient=mortalidadWebClient;
    }

    //logica del dashboard vet

    public DashboardVetResponse obtenerDashboardVet(Integer jaulaId) {
        DashboardVetResponse dashboard = new DashboardVetResponse();
        dashboard.setJaulaId(jaulaId);

        try{
            dashboard.setAlertas(consultarAlertas(jaulaId));
        }catch (ResourceNotFoundException | ServicioExternoException e) {
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
            .bodyToMono(AlertasResponse.class)
            .block();
        }catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("No se encontraron alertas para esta jaula: " + jaulaId);
        }catch (WebClientException ex) {
            throw new ServicioExternoException ("No se pudo conectar al servicio de alertas", ex);
        }
    }

    private MonitoreoAResponse consultarMonitoreoA(int jaulaId) {
        try{
            return monitoreoAWebClient.get()
            .uri("/jaula/{id}", jaulaId)
            .retrieve()
            .bodyToMono(MonitoreoAResponse.class)
            .block();
        }catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("No se encontraron datos de Monitoreo Ambiental");
        }catch (WebClientException ex) {
            throw new ServicioExternoException("No se pudo conectar al servicio de Monitoreo Ambiental")
        }
    }

    private MortalidadResponse consultarMortalidad(int jaulaId) {
        try{
            return mortalidadWebClient.get()
            .uri("jaula/{id}", jaulaId)
            .retrieve()
            .bodyToMono(MortalidadResponse.class)
            .block();
        }catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException( "No hay registros de mortalidad");
        }catch (WebClientException ex) {
            throw new ServicioExternoException("No se pudo conectar al servio de mortalidad");
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