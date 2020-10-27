package pe.tuna.servitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServitemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServitemApplication.class, args);
    }

}
