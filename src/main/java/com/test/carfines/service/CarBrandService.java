package com.test.carfines.service;

import com.test.carfines.model.CarBrand;

import java.util.List;

public interface CarBrandService {
    CarBrand addCarBrand (CarBrand carBrand);
    void delete(long id);
    CarBrand getByName(String name);
    CarBrand editCarBrand (CarBrand carBrand);
    List<CarBrand> getAll();
}
