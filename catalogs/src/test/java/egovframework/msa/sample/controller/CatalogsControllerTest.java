package egovframework.msa.sample.controller;

import egovframework.msa.sample.service.CustomerApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class CatalogsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CustomerApiService customerApiService;

    static final String BASE_URL = "http://localhost:8082";
    static final String CUSTOMER_URI = "/api/v1/customers/";

    @Test
    public void getCustomer_단독실행() throws Exception {
        // given
        String customerId = "test";

        // then
        mockMvc.perform(get("/catalogs/customers/" + customerId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(customerId)));
    }

    @Test
    public void getApiCustomer_호출확인() throws Exception {
        // given
        String customerId = "customer_api";
        String uri = CUSTOMER_URI + customerId;

        // then
        WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl(BASE_URL).build();
        webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(stringEntityExchangeResult -> {
                    log.info(stringEntityExchangeResult.getResponseBody());
                });
    }

    @Test
    public void getApiCustomer_Hystrix_오류발생확인() throws Exception {
        // given
        String customerId = "customer_api";
        String uri = CUSTOMER_URI + "exception/" + customerId;

        // then
        WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl(BASE_URL).build();
        webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    public void getApiCustomer_Hystrix_fallback확인() throws Exception {
        // given
        String customerId = "customer_api";

        // when
        String message = customerApiService.getCustomerApiException(customerId);
        log.error(message);

        // then
        assertThat(message.contains("지연되고 있습니다"));
    }
}