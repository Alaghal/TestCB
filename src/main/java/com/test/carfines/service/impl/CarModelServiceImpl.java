package com.test.carfines.service.impl;

import com.test.carfines.model.CarModel;

import com.test.carfines.repository.CarModelRepository;
import com.test.carfines.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository repository;

    @Override
    public CarModel addCarModel(CarModel carModel) {
        return repository.saveAndFlush( carModel );
    }

    @Override
    public void delete(long id) {
         repository.deleteById( id );
    }

    @Override
    public CarModel getByName(String carModelName) {
        return repository.findByName( carModelName );
    }

    @Override
    public CarModel editCarModel(CarModel carModel) {
        return repository.saveAndFlush( carModel );
    }

    @Override
    public List<CarModel> getAll() {
        return repository.findAll();
    }
}
