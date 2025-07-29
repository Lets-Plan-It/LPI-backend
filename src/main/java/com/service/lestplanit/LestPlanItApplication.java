package com.service.lestplanit;

import com.service.lestplanit.security.services.UserService;
import com.service.lestplanit.utils.InitialDataCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class LestPlanItApplication {

    @Autowired
    private UserService userService;
    @Autowired
    private InitialDataCreator initialDataCreator;

    public static void main(String[] args) {
        SpringApplication.run(LestPlanItApplication.class, args);
    }

    //    Initially we are going to use this user to access to the service.
    @Bean
    CommandLineRunner init() {
        return args -> {
            initialDataCreator.loadInitialData();
        };
    }
}
