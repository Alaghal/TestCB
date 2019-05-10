package com.test.carfines.service;


import com.test.carfines.model.CarModel;

import java.util.List;

public interface CarModelService {
    boolean addCarModel (CarModel carModel);
    boolean delete(long id);
    CarModel getByName(String name);
    boolean editCarModel (CarModel carModel);
    List<CarModel> getAll();
}
