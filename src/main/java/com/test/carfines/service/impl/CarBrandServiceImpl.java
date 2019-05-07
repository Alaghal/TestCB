package com.test.carfines.service.impl;

import com.test.carfines.model.CarBrand;
import com.test.carfines.repository.CarBrandRepository;
import com.test.carfines.service.CarBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    public CarBrand addCarBrand(CarBrand carBrand) {
        CarBrand carBrandSave = carBrandRepository.saveAndFlush( carBrand );

        return carBrandSave;
    }

    @Override
    public void delete(long id) {
         carBrandRepository.deleteById( id );
    }

    @Override
    public CarBrand getByName(String name) {
        return carBrandRepository.findByName( name );
    }

    @Override
    public CarBrand editCarBrand(CarBrand carBrand) {
        return carBrandRepository.saveAndFlush( carBrand );
    }

    @Override
    public List<CarBrand> getAll() {
        List<CarBrand> brandList = carBrandRepository.findAll();
        return brandList;
    }
}
