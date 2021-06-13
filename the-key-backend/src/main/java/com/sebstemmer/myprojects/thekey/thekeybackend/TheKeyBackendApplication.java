package com.sebstemmer.myprojects.thekey.thekeybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TheKeyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheKeyBackendApplication.class, args);
    }

}
