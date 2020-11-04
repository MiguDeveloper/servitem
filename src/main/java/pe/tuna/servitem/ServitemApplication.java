package pe.tuna.servitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// como ya tenemos habilitado Eureka no necesitamos el Ribbonclient
// @RibbonClient(name = "servicio-productos")

// EnableEurekaClient: No es necesario pero podemos habilitar de forma explicita como un eureka client
// EnableCircuitBreaker: habilitamos Hystrix

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ServitemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServitemApplication.class, args);
    }

}
