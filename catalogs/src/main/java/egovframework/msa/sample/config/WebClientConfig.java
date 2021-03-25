package egovframework.msa.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        // build()를 통해 생성시 timeout, filter, header 정보등을 공통으로 관리할 수 있다
        return WebClient.builder().build();
    }
}
