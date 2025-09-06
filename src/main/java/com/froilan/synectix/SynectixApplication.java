package com.froilan.synectix;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SynectixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SynectixApplication.class, args);
    }

}
