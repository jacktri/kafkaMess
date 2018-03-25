package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerApp implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Producer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}