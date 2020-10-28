package pe.tuna.servitem;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    // utilizamos la anotacion de balanceo de carga para
    // poder obtener la mejor instancia disponible
    @Bean("clienteRest")
    @LoadBalanced
    public RestTemplate registrarRestTemplate(){
        return new RestTemplate();
    }
}
