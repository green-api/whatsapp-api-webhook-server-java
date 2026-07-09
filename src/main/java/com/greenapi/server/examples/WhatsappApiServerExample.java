package com.greenapi.server.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.greenapi.server")
public class WhatsappApiServerExample {

    public static void main(String[] args) {
        SpringApplication.run(WhatsappApiServerExample.class, args);
    }
}