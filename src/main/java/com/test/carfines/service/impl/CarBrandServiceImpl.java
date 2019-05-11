package com.test.carfines.service.impl;

import com.test.carfines.domain.CarBrand;
import com.test.carfines.repository.CarBrandRepository;
import com.test.carfines.service.CarBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CarBrandServiceImpl implements CarBrandService {
    private final CarBrandRepository repository;


    public CarBrandServiceImpl(CarBrandRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean addCarBrand(CarBrand carBrand) {
        var carBrandFromDB = Optional.ofNullable(repository.findByName( carBrand.getCarBrandName() ));
        if (carBrandFromDB.isPresent()) {
            return false;
        }

        repository.saveAndFlush( carBrand );
        log.info( "Added " + carBrand.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public boolean delete(long id) {
        Optional<CarBrand> carBrandFromDB = repository.findById( id );

        if(carBrandFromDB.isEmpty()){
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + carBrandFromDB.get().toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public CarBrand getByName(String nameCarBrand) {
        return repository.findByName( nameCarBrand );
    }

    @Override
    public boolean editCarBrand(CarBrand carBrand) {
        Optional<CarBrand> carBrandFromDB = repository.findById( carBrand.getId() );
        if (carBrandFromDB.isEmpty()) {
            return false;
        }
        repository.saveAndFlush( carBrand );
        log.info( "Edit  old version = " + carBrandFromDB.get().toString() + ", new version =" + carBrand.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public List<CarBrand> getAll() {
        return Optional.ofNullable(repository.findAll()).orElse( new ArrayList<>( ) );
    }
}
