package com.test.carfines.service;


import com.test.carfines.model.CarModel;

import java.util.List;

public interface CarModelService {
    CarModel addCarModel (CarModel carModel);
    void delete(long id);
    CarModel getByName(String name);
    CarModel editCarModel (CarModel carModel);
    List<CarModel> getAll();
}
