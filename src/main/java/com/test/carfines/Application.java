package com.test.carfines;

import com.test.carfines.model.CarBrand;
import com.test.carfines.repository.CarBrandRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@Slf4j
class Application {

    public static void main(String[] args) {
        SpringApplication.run( Application.class, args );
    }


}

