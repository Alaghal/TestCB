package com.test.carfines.service.impl;

import com.test.carfines.domain.CarModel;
import com.test.carfines.repository.CarModelRepository;
import com.test.carfines.service.CarModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository repository;


    public CarModelServiceImpl(CarModelRepository repository){
        this.repository=repository;
    }

    @Override
    public boolean addCarModel(CarModel carModel) {
        var carModelFromDB = Optional.ofNullable(repository.findByName( carModel.getCarModelName() ));
        if (carModelFromDB.isPresent()) {
            return false;
        }
        repository.saveAndFlush( carModel );
        log.info("Added "+carModel.toString()+" "+ LocalDate.now() );
        return true;
    }

    @Override
    public boolean delete(long id) {
        var carModelForLog = Optional.ofNullable(  repository.findById( id ));
        if(carModelForLog.isEmpty()){
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
        var carModelFromDB = Optional.ofNullable(  repository.findById( carModel.getId() ));
        if (carModelFromDB.isEmpty()) {
            return false;
        }
        repository.saveAndFlush( carModel );
        log.info("Edit  old version = "+carModelFromDB.get().toString()+", new version ="+carModel.toString()+" "+ LocalDate.now() );
        return true;
    }

    @Override
    public List<CarModel> getAll() {
        return Optional.ofNullable(repository.findAll()).orElse( new ArrayList<>( ) );
    }
}
