package egovframework.msa.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

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
    public void getApiCustomer() throws Exception {
        // given
        String customerId = "customer_api";
        String baseUrl = "http://localhost:8082";
        String uri = "/api/v1/customers/" + customerId;

        // then
        WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl).build();
        webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(stringEntityExchangeResult -> {
                    log.info(stringEntityExchangeResult.getResponseBody());
                });
    }
}