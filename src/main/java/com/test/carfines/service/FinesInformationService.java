package com.test.carfines.service;

import com.test.carfines.model.FinesInformation;

import java.util.List;

public interface FinesInformationService {
    FinesInformation addFinesInformation (FinesInformation finesInformation);
    boolean delete(long id);
    FinesInformation getFinesInformationById(long id);
    boolean editFinesInformation (FinesInformation finesInformation);
    List<FinesInformation> getAll();
}
