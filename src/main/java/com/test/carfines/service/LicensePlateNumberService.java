package com.test.carfines.service;


import com.test.carfines.domain.LicensePlateNumber;

import java.util.List;

public interface LicensePlateNumberService {
    LicensePlateNumber addLicensePlateNumber (LicensePlateNumber licensePlateNumber);
    boolean delete(long id);
    List <LicensePlateNumber> findByIdCarModelAndIdOwner (long idCarModel, long idOwner);
    LicensePlateNumber getLicensePlateNumberById (long id);
    boolean editLicensePlateNumber (LicensePlateNumber licensePlateNumber);
    List<LicensePlateNumber> getAll();
}
