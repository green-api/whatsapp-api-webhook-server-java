package com.greenapi.server.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Use "main" in method name instead "example" in real code.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.greenapi.server")
public class WhatsappApiServerExample {
    public static void example(String[] args) {
        SpringApplication.run(WhatsappApiServerExample.class, args);
    }
}