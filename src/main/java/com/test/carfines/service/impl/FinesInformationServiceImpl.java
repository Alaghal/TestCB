package com.test.carfines.service.impl;

import com.test.carfines.model.CarModel;
import com.test.carfines.model.FinesInformation;
import com.test.carfines.repository.FinesInformationRepository;
import com.test.carfines.service.FinesInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FinesInformationServiceImpl implements FinesInformationService {
    private final FinesInformationRepository repository;

    @Autowired
    public FinesInformationServiceImpl(FinesInformationRepository repository) {
        this.repository = repository;
    }

    @Override
    public FinesInformation addFinesInformation(FinesInformation finesInformation) {
        FinesInformation finesInformationSave = repository.saveAndFlush( finesInformation );

        if(finesInformationSave == null){
            return null;
        }

        log.info( "Added " + finesInformationSave.toString() + " " + LocalDate.now() );
        return finesInformationSave;
    }

    @Override
    public boolean delete(long id) {
        Optional<FinesInformation> finesInformationFromDB = repository.findById( id );

        if (finesInformationFromDB == null) {
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + finesInformationFromDB.get().toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public FinesInformation getFinesInformationById(long id) {
        return repository.findById( id ).get();
    }


    @Override
    public boolean editFinesInformation(FinesInformation finesInformation) {
        Optional<FinesInformation> finesInformationFromDB = repository.findById( finesInformation.getId() );
        if (finesInformationFromDB == null) {
            return false;
        }
        repository.saveAndFlush( finesInformation );
        log.info("Edit  old version = "+finesInformationFromDB.toString()+", new version ="+finesInformation.toString()+" "+ LocalDate.now() );
        return true;

    }

    @Override
    public List<FinesInformation> getAll() {
        return repository.findAll();
    }
}
