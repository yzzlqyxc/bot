package com.kop.machingsystem;

import com.kop.machingsystem.service.impl.MatchingServiceImply;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatchingSystemApplication {
    public static void main(String[] args) {
        MatchingServiceImply.matchingPool.start();
        SpringApplication.run(MatchingSystemApplication.class, args);
    }
}