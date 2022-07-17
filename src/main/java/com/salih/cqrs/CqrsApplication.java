package com.salih.cqrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class CqrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqrsApplication.class, args);
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}
