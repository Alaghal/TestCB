package com.test.carfines.service.impl;

import com.test.carfines.domain.FinesInformation;
import com.test.carfines.domain.FrequentFinesDTO;
import com.test.carfines.repository.FinesInformationRepository;
import com.test.carfines.service.FinesInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinesInformationServiceImpl implements FinesInformationService {
    private final FinesInformationRepository repository;


    public FinesInformationServiceImpl(FinesInformationRepository repository) {
        this.repository = repository;
    }

    @Override
    public FinesInformation addFinesInformation(FinesInformation finesInformation) {
        var finesInformationSave = Optional.ofNullable(repository.saveAndFlush( finesInformation ));

        if(finesInformationSave.isEmpty()){
            return null;
        }

        log.info( "Added " + finesInformationSave.get().toString() + " " + LocalDate.now() );
        return finesInformationSave.get();
    }

    @Override
    public boolean delete(long id) {
        Optional<FinesInformation> finesInformationFromDB = repository.findById( id );

        if (finesInformationFromDB.isEmpty()) {
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + finesInformationFromDB.get().toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public FinesInformation getFinesInformationById(long id) {
        return repository.findById( id ).orElse( new FinesInformation( ) );
    }


    @Override
    public boolean editFinesInformation(FinesInformation finesInformation) {
        Optional<FinesInformation> finesInformationFromDB = repository.findById( finesInformation.getId() );
        if (finesInformationFromDB.isEmpty()) {
            return false;
        }
        repository.saveAndFlush( finesInformation );
        log.info("Edit  old version = "+finesInformationFromDB.get().toString()+", new version ="+finesInformation.toString()+" "+ LocalDate.now() );
        return true;

    }

    @Override
    public List<FinesInformation> getAll() {
        return Optional.ofNullable(repository.findAll()).orElse( new ArrayList<>( ) );
    }


    @Override
    public List<FinesInformation> getFinesInformation(String ownerName, String licensePlateNumberName) {
        if(!ownerName.isEmpty() && !licensePlateNumberName.isEmpty()){
            return Optional.ofNullable( repository.findFinesInformationByLPNAndOwnerName( ownerName,licensePlateNumberName ) ).orElse( new ArrayList<>(  ));
        }

        if(!ownerName.isEmpty()){
            return Optional.ofNullable(repository.findFinesInformationByOwnerName( ownerName ) ).orElse( new ArrayList<>(  ));
        }

        if(!licensePlateNumberName.isEmpty()){
            return Optional.ofNullable( repository.findFinesInformationByLicensePlateNumbersName(licensePlateNumberName ) ).orElse( new ArrayList<>(  ) );
        }

        return getAll();
    }

    @Override
    public List<FrequentFinesDTO> getFrequentFines() {
        int maxCountElements=5;
        return repository.findFrequentFines().stream().limit( maxCountElements ).collect( Collectors.toList() );
    }


}
