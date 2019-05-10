package com.test.carfines.service.impl;

import com.test.carfines.model.CarBrand;
import com.test.carfines.repository.CarBrandRepository;
import com.test.carfines.service.CarBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CarBrandServiceImpl implements CarBrandService {
    private final CarBrandRepository repository;

    @Autowired
    public CarBrandServiceImpl(CarBrandRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean addCarBrand(CarBrand carBrand) {
        var carBrandFromDB = repository.findByName( carBrand.getCarBrandName() );
        if (carBrandFromDB != null) {
            return false;
        }

        repository.saveAndFlush( carBrand );
        log.info( "Added " + carBrand.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public boolean delete(long id) {
        Optional<CarBrand> carBrandFromDB = repository.findById( id );

        if(carBrandFromDB == null){
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + carBrandFromDB.toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public CarBrand getByName(String nameCarBrand) {
        return repository.findByName( nameCarBrand );
    }

    @Override
    public boolean editCarBrand(CarBrand carBrand) {
        Optional<CarBrand> carBrandFromDB = repository.findById( carBrand.getId() );
        if (carBrandFromDB == null) {
            return false;
        }
        repository.saveAndFlush( carBrand );
        log.info( "Edit  old version = " + carBrandFromDB.toString() + ", new version =" + carBrand.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public List<CarBrand> getAll() {
        return repository.findAll();
    }
}
