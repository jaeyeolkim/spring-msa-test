package egovframework.msa.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient(){
        // build()를 통해 생성시 timeout, filter, header 정보등을 공통으로 관리할 수 있다
        // @LoadBalanced Ribbon 처리가 정상적으로 이루어지기 위해서는 build()를 하지 않아야 된다. build()까지 할 경우 unknown 오류 발생.
        return WebClient.builder();
    }

}
