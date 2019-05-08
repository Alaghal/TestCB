package com.test.carfines.service;

import com.test.carfines.model.FinesInformation;
import com.test.carfines.model.LicensePlateNumber;

import java.util.List;

public interface LicensePlateNumberService {
    LicensePlateNumber addLicensePlateNumber (LicensePlateNumber licensePlateNumber);
    void delete(long id);
    LicensePlateNumber editLicensePlateNumber (LicensePlateNumber licensePlateNumber);
    List<LicensePlateNumber> getAll();
}
