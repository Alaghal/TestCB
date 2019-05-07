package com.test.carfines;

import com.test.carfines.repository.CarBrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Slf4j
class Application {

    public static void main(String[] args) {
        SpringApplication.run( Application.class, args );
    }

    @Bean
    public CommandLineRunner runner(CarBrandRepository repository) {

        return r ->log.info(repository.findAll().toString());
    }
}

