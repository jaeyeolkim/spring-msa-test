package egovframework.msa.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@RestController
public class ElkController {
    private static final Logger logger = LoggerFactory.getLogger(ElkController.class);

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/elk")
    public String hello(){
        String response = "Hello ELK " + new Date();
        logger.info(response);
        return response;
    }

    @GetMapping("/exception")
    public String createException(){
        String response = "";
        try {
            throw new Exception("Exception has occured....");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            String stackTrace = sw.toString();
            logger.error("Exception - " + stackTrace);
            response = stackTrace;
        }
        return response;
    }
}
