package com.test.carfines.service.impl;

import com.test.carfines.model.FinesInformation;
import com.test.carfines.repository.FinesInformationRepository;
import com.test.carfines.service.FinesInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FinesInformationServiceImpl implements FinesInformationService {

    @Autowired
    private FinesInformationRepository repository;

    @Override
    public FinesInformation addFinesInformation(FinesInformation finesInformation) {
        return repository.saveAndFlush( finesInformation );
    }

    @Override
    public void delete(long id) {
          repository.deleteById( id );
    }

    @Override
    public FinesInformation editFinesInformation(FinesInformation finesInformation) {
        return repository.saveAndFlush( finesInformation );
    }

    @Override
    public List<FinesInformation> getAll() {
        return repository.findAll();
    }
}
