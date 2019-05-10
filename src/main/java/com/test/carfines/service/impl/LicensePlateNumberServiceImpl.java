package com.test.carfines.service.impl;

import com.test.carfines.model.LicensePlateNumber;
import com.test.carfines.repository.LicensePlateNumberRepository;
import com.test.carfines.service.LicensePlateNumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LicensePlateNumberServiceImpl implements LicensePlateNumberService {

    private final LicensePlateNumberRepository repository;

    @Autowired
    public LicensePlateNumberServiceImpl(LicensePlateNumberRepository repository){
        this.repository=repository;
    }

    @Override
    public LicensePlateNumber addLicensePlateNumber(LicensePlateNumber licensePlateNumber) {
        LicensePlateNumber licensePlateNumberSave= repository.saveAndFlush( licensePlateNumber );
        if(licensePlateNumberSave == null){
            return null;
        }
        log.info( "Added " + licensePlateNumberSave.toString() + " " + LocalDate.now() );
        return licensePlateNumberSave;
    }

    @Override
    public boolean delete(long id) {
        Optional<LicensePlateNumber> licensePlateNumberFromDB = repository.findById( id );

        if (licensePlateNumberFromDB == null) {
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + licensePlateNumberFromDB.get().toString() + " " + LocalDate.now() );

        return  true;
    }

    @Override
    public List<LicensePlateNumber> findByIdCarModelAndIdOwner(long idCarModel, long idOwner) {
        return repository.findByIdCarModelAndIdOwner( idCarModel, idOwner);
    }

    @Override
    public LicensePlateNumber getLicensePlateNumberById(long id) {
        return repository.findById( id ).get();
    }

    @Override
    public boolean editLicensePlateNumber(LicensePlateNumber licensePlateNumber) {
        var licensePlateNumberFromDB = repository.findById( licensePlateNumber.getId() );
        if (licensePlateNumberFromDB == null) {
            return false;
        }
        repository.saveAndFlush( licensePlateNumber );
        log.info("Edit  old version = "+licensePlateNumberFromDB.toString()+", new version ="+licensePlateNumber.toString()+" "+ LocalDate.now() );
        return true;
    }

    @Override
    public List<LicensePlateNumber> getAll() {
        return repository.findAll();
    }
}
