package egovframework.msa.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CustomersController {

    @GetMapping("/api/v1/customers/{customerId}")
    public String getCustomer(@PathVariable String customerId) {
        log.info("API response customer = {}", customerId);
        return String.format("[Customer ID = %s, at %s]", customerId, System.currentTimeMillis());
    }
}
