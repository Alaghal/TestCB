package com.test.carfines.service.impl;

import com.test.carfines.domain.LicensePlateNumber;
import com.test.carfines.repository.LicensePlateNumberRepository;
import com.test.carfines.service.LicensePlateNumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LicensePlateNumberServiceImpl implements LicensePlateNumberService {

    private final LicensePlateNumberRepository repository;


    public LicensePlateNumberServiceImpl(LicensePlateNumberRepository repository){
        this.repository=repository;
    }

    @Override
    public LicensePlateNumber addLicensePlateNumber(LicensePlateNumber licensePlateNumber) {
        var licensePlateNumberSave= Optional.ofNullable(repository.saveAndFlush( licensePlateNumber ));
        if(licensePlateNumberSave.isEmpty()){
            return null;
        }
        log.info( "Added " + licensePlateNumberSave.get().toString() + " " + LocalDate.now() );
        return licensePlateNumberSave.get();
    }

    @Override
    public boolean delete(long id) {
       var licensePlateNumberFromDB = Optional.ofNullable(  repository.findById( id ));

        if (licensePlateNumberFromDB.isEmpty()) {
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + licensePlateNumberFromDB.get().toString() + " " + LocalDate.now() );

        return  true;
    }

    @Override
    public List<LicensePlateNumber> findByIdCarModelAndIdOwner(long idCarModel, long idOwner) {
        return Optional.ofNullable(repository.findByIdCarModelAndIdOwner( idCarModel, idOwner)).orElse( new ArrayList<>(  ) );
    }

    @Override
    public LicensePlateNumber getLicensePlateNumberById(long id) {
        return  repository.findById( id ).orElse( new LicensePlateNumber( ) );
    }

    @Override
    public boolean editLicensePlateNumber(LicensePlateNumber licensePlateNumber) {
        var licensePlateNumberFromDB =  Optional.ofNullable( repository.findById( licensePlateNumber.getId() ));
        if (licensePlateNumberFromDB.isEmpty()) {
            return false;
        }
        repository.saveAndFlush( licensePlateNumber );
        log.info("Edit  old version = "+licensePlateNumberFromDB.get().toString()+", new version ="+licensePlateNumber.toString()+" "+ LocalDate.now() );
        return true;
    }

    @Override
    public List<LicensePlateNumber> getAll() {
        return Optional.ofNullable(repository.findAll()).orElse( new ArrayList<>( ) );
    }
}
