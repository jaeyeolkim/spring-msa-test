package egovframework.msa.sample.controller;

import egovframework.msa.sample.service.CustomerApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CatalogsController {

    private final CustomerApiService customerApiService;

    @GetMapping("/catalogs/customers/{customerId}")
    public String getCustomer(@PathVariable String customerId) {
        String customer = customerApiService.getCustomer(customerId);
        log.info("response customer = {}", customer);
        return String.format("[Customer ID = %s, Response Customer = %s at %s]", customerId, customer, System.currentTimeMillis());
    }

    @GetMapping("/api/v1/catalogs/customers/{customerId}")
    public String getApiCustomer(@PathVariable String customerId) {
        return customerApiService.getCustomerApi(customerId);
    }

    @GetMapping("/api/v1/catalogs/customers/exception/{customerId}")
    public String getApiCustomerException(@PathVariable String customerId) {
        return customerApiService.getCustomerApiException(customerId);
    }
}
