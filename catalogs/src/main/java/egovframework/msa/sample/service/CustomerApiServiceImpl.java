package egovframework.msa.sample.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerApiServiceImpl implements CustomerApiService {

    private final WebClient webClient;
    private static final String CUSTOMER_URL = "http://localhost:8082/api/v1/customers";

    @Override
    public String getCustomer(String customerId) {
        return customerId;
    }

    @Override
    public String getCustomerApi(String customerId) {
        String response = webClient.get()
                .uri(CUSTOMER_URL + "/{customerId}", customerId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info(response);
        return response;
    }

    @HystrixCommand(fallbackMethod = "getCustomerFallback")
    @Override
    public String getCustomerApiException(String customerId) {
        String response = webClient.get()
                .uri(CUSTOMER_URL + "/exception/{customerId}", customerId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info(response);
        return response;
    }

    public String getCustomerFallback(String customerId, Throwable e){
        log.error(e.getMessage());
        return "고객정보 조회가 지연되고 있습니다.";
    }
}
