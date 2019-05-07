package com.test.carfines.service;

import com.test.carfines.model.CarModel;
import com.test.carfines.model.FinesInformation;

import java.util.List;

public interface FinesInformationService {
    FinesInformation addFinesInformation (FinesInformation finesInformation);
    void delete(long id);
    FinesInformation getByName(String name);
    FinesInformation editFinesInformation (FinesInformation finesInformation);
    List<FinesInformation> getAll();
}
