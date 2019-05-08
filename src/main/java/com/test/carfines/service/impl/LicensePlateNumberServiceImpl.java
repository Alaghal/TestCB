package com.test.carfines.service.impl;

import com.test.carfines.model.LicensePlateNumber;
import com.test.carfines.repository.LicensePlateNumberRepository;
import com.test.carfines.service.LicensePlateNumberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LicensePlateNumberServiceImpl implements LicensePlateNumberService {

    @Autowired
    private LicensePlateNumberRepository repository;

    @Override
    public LicensePlateNumber addLicensePlateNumber(LicensePlateNumber licensePlateNumber) {
        return repository.saveAndFlush( licensePlateNumber );
    }

    @Override
    public void delete(long id) {
          repository.deleteById( id );
    }

    @Override
    public LicensePlateNumber editLicensePlateNumber(LicensePlateNumber licensePlateNumber) {
        return repository.saveAndFlush( licensePlateNumber );
    }

    @Override
    public List<LicensePlateNumber> getAll() {
        return repository.findAll();
    }
}
