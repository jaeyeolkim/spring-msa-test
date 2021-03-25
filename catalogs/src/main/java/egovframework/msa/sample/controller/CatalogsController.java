package egovframework.msa.sample.controller;

import egovframework.msa.sample.service.CustomerApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CatalogsController {

    private final CustomerApiService customerApiService;
    private final WebClient webClient;

    @GetMapping("/catalogs/customers/{customerId}")
    public String getCustomer(@PathVariable String customerId) {
        String customer = customerApiService.getCustomer(customerId);
        log.info("response customer = {}", customer);
        return String.format("[Customer ID = %s, Response Customer = %s at %s]", customerId, customer, System.currentTimeMillis());
    }

    @GetMapping("/api/v1/catalogs/customers/{customerId}")
    public String getApiCustomer(@PathVariable String customerId) {
        String response = webClient.get()
                .uri("http://localhost:8082/api/v1/customers/{customerId}", customerId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}
