package egovframework.msa.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class CatalogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogsApplication.class, args);
    }

}
