package com.hans.rboard;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Webflux board II", version = "0.0", description = "Created 2021/11/12"))
public class WebfluxBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxBoardApplication.class, args);
    }

}
