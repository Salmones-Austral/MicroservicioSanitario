package cl.SalmonesAustral.Sanitario.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    //ms de alertas

     @Value
    ("${alertas.service.url}")
    private String alertasUrl;

    @Bean 
    public WebClient alertasWebClient() {
        return WebClient.builder()
        .baseUrl(alertasUrl)
        .build();
    }

    //ms de monitoreo ambiental

    @Value
    ("${monitoreo.service.url}")
    private String monitoreoUrl;
   
    @Bean
    public WebClient monitoreoAWebClient() {
        return WebClient.builder()
        .baseUrl(monitoreoUrl)
        .build();
    }

    @Value
    ("${mortalidad.service.url}")
    private String mortalidadUrl;

    @Bean
    public WebClient mortalidadWebClient() {
        return WebClient.builder()
        .baseUrl(mortalidadUrl)
        .build();
    }


/* 
 //webclient de ms alertas
    @Bean ("alertasWebClient")
    public WebClient alertasWebClient(WebClient.Builder builder,
    @Value("${alertas.service.url:http://localhost:8083/api/v1/alertas}") String alertasServiceUrl) {
        return builder.baseUrl(alertasServiceUrl).build();
    }
    //webclient de ms monitoreo ambiental
    @Bean("monitoreoWebClient")
    public WebClient monitoreoWebClient(WebClient.Builder builder,
    @Value("${monitoreo.service.url:http://localhost:8090/api/v1/monitoreo}") String monitoreoServiceUrl) {
        return builder.baseUrl(monitoreoServiceUrl).build();
    }

    //webclient de ms mortalidad
    @Bean("mortalidadWebClient")
    public WebClient mortalidadWebClient(WebClient.Builder builder,
    @Value("${mortalidad.service.url:http://localhost:8082/api/v1/mortalidad") String mortalidadServiceUrl) {
        return builder.baseUrl(mortalidadServiceUrl).build();
    }
*/    
}


 
