package com.test.carfines.service.impl;

import com.test.carfines.model.CarBrand;

import com.test.carfines.repository.CarBrandRepository;
import com.test.carfines.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandRepository repository;

    @Override
    public CarBrand addCarBrand(CarBrand carBrand) {
        return repository.saveAndFlush( carBrand );
    }

    @Override
    public void delete(long id) {
         repository.deleteById( id );
    }

    @Override
    public CarBrand getByName(String nameCarBrand) {
        return repository.findByName( nameCarBrand );
    }

    @Override
    public CarBrand editCarBrand(CarBrand carBrand) {
        return repository.saveAndFlush( carBrand );
    }

    @Override
    public List<CarBrand> getAll() {
        return repository.findAll();
    }
}
