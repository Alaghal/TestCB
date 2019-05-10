package com.test.carfines.service.impl;

import com.test.carfines.model.CarModel;
import com.test.carfines.repository.CarModelRepository;
import com.test.carfines.service.CarModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository repository;

    @Autowired
    public CarModelServiceImpl(CarModelRepository repository){
        this.repository=repository;
    }

    @Override
    public boolean addCarModel(CarModel carModel) {
        var carModelFromDB = repository.findByName( carModel.getCarModelName() );
        if (carModelFromDB != null) {
            return false;
        }
        repository.saveAndFlush( carModel );
        log.info("Added "+carModel.toString()+" "+ LocalDate.now() );
        return true;
    }

    @Override
    public boolean delete(long id) {
        Optional<CarModel> carModelForLog = repository.findById( id );
        if(carModelForLog == null){
            return false;
        }
        repository.deleteById( id );

        log.info("Delete "+carModelForLog.get().toString()+" "+ LocalDate.now() );

        return true;
    }

    @Override
    public CarModel getByName(String carModelName) {
        return repository.findByName( carModelName );
    }

    @Override
    public boolean editCarModel(CarModel carModel) {
        Optional<CarModel> carModelFromDB = repository.findById( carModel.getId() );
        if (carModelFromDB == null) {
            return false;
        }
        repository.saveAndFlush( carModel );
        log.info("Edit  old version = "+carModelFromDB.toString()+", new version ="+carModel.toString()+" "+ LocalDate.now() );
        return true;
    }

    @Override
    public List<CarModel> getAll() {
        return repository.findAll();
    }
}
