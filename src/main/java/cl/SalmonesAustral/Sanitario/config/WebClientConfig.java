package cl.SalmonesAustral.Sanitario.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    //webclient de ms alertas
    @Bean ("alertasWebClient")
    public WebClient alertasWebClient(WebClient.Builder builder,
    @Value("${alertas.service.url:http://localhost:8083/api/v1/alertas}") String alertasServiceUrl) {
        return builder.baseUrl(alertasServiceUrl).build();
    }
    //webclient de ms monitoreo ambiental
    @Bean("monitoreoWebClient")
    public WebClient monitoreoWebClient(WebClient.Builder builder,
    @Value("${monitoreo.service.url:http://localhost:8090/api/v1/mortalidad}") String monitoreoServiceUrl) {
        return builder.baseUrl(monitoreoServiceUrl).build();
    }

    //webclient de ms mortalidad
    @Bean("monitoreoWebClient")
    public WebClient mortalidadWebClient(WebClient.Builder builder,
    @Value("${mortalidad.service.url:http://localhost:8082/api/v1/mortalidad") String mortalidadServiceUrl) {
        return builder.baseUrl(mortalidadServiceUrl).build();
    }
    
}


 
