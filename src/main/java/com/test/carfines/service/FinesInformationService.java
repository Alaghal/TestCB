package com.test.carfines.service;

import com.test.carfines.domain.FinesInformation;
import com.test.carfines.domain.FrequentFinesDTO;

import java.util.List;

public interface FinesInformationService {
    boolean delete(long id);
    boolean editFinesInformation (FinesInformation finesInformation);

    FinesInformation getFinesInformationById(long id);
    FinesInformation addFinesInformation (FinesInformation finesInformation);

    List<FinesInformation>  getAll();
    List<FinesInformation>  getFinesInformation(String ownerName, String licensePlateNumberName);
    List<FrequentFinesDTO> getFrequentFines();
}
