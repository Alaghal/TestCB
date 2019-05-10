package com.test.carfines.service.impl;

import com.test.carfines.model.Owner;
import com.test.carfines.repository.OwnerRepository;
import com.test.carfines.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository repository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addOwner(Owner owner) {
        var ownerFromDB = repository.findByName( owner.getNameOwner() );
        if (ownerFromDB != null) {
            return false;
        }
        repository.saveAndFlush( owner );
        log.info( "Added " + owner.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public boolean delete(long id) {
        Optional<Owner> ownerFromDB = repository.findById( id );

        if (ownerFromDB == null) {
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + ownerFromDB.get().toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public Owner getByName(String name) {
        return repository.findByName( name );
    }

    @Override
    public boolean editOwner(Owner owner) {
        Optional<Owner> ownerFromDB = repository.findById( owner.getId() );
        if (ownerFromDB == null) {
            return false;
        }
        repository.saveAndFlush( owner );
        log.info( "Edit  old version = " + ownerFromDB.toString() + ", new version =" + owner.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public List<Owner> getAll() {
        return repository.findAll();
    }
}
