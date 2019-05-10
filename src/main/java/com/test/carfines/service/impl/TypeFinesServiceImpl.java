package com.test.carfines.service.impl;

import com.test.carfines.model.TypeFines;

import com.test.carfines.repository.TypeFinesRepository;
import com.test.carfines.service.TypeFinesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TypeFinesServiceImpl implements TypeFinesService {

    private final TypeFinesRepository repository;

    @Autowired
    public TypeFinesServiceImpl(TypeFinesRepository repository){
        this.repository=repository;
    }

    @Override
    public boolean addTypeFines(TypeFines typeFines) {
        var ownerFromDB = repository.findByName( typeFines.getNameTypeFines() );
        if (ownerFromDB != null) {
            return false;
        }
        repository.saveAndFlush( typeFines );
        log.info( "Added " + typeFines.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public boolean delete(long id) {
        Optional<TypeFines> ownerFromDB = repository.findById( id );

        if (ownerFromDB == null) {
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + ownerFromDB.toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public TypeFines getByName(String name) {
        return  repository.findByName( name );
    }

    @Override
    public boolean editTypeFines(TypeFines typeFines) {
       Optional<TypeFines> typeFinesFromDB = repository.findById( typeFines.getId() );
        if (typeFinesFromDB == null) {
            return false;
        }
        repository.saveAndFlush( typeFines );
        log.info( "Edit  old version = " + typeFinesFromDB.toString() + ", new version =" + typeFines.toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public List<TypeFines> getAll() {
        return repository.findAll();
    }
}
