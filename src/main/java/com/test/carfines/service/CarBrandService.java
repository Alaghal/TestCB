package com.test.carfines.service;

import com.test.carfines.model.CarBrand;

import java.util.List;

public interface CarBrandService {
    boolean addCarBrand (CarBrand carBrand);
    boolean delete(long id);
    CarBrand getByName(String name);
    boolean editCarBrand (CarBrand carBrand);
    List<CarBrand> getAll();
}
