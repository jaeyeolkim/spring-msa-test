package egovframework.msa.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@RestController
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/ping")
    public String callElkEndPoint(){
        // end-point를 직접 호출후 결과값을 String의 형태로 변환하여 forObject에 담는다.
        final String forObject = restTemplate.getForObject("http://localhost:8080/elk", String.class);
        logger.info(forObject);
        return forObject;
    }

}
