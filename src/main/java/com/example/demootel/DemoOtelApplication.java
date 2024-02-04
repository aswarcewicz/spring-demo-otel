package com.example.demootel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class DemoOtelApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOtelApplication.class, args);
        Hooks.enableAutomaticContextPropagation();
    }

}
