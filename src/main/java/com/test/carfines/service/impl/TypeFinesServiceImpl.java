package com.test.carfines.service.impl;

import com.test.carfines.model.TypeFines;

import com.test.carfines.repository.TypeFinesRepository;
import com.test.carfines.service.TypeFinesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TypeFinesServiceImpl implements TypeFinesService {

    @Autowired
     private TypeFinesRepository repository;

    @Override
    public TypeFines addTypeFines(TypeFines typeFines) {
        return repository.saveAndFlush( typeFines );
    }

    @Override
    public void delete(long id) {
        repository.deleteById( id );
    }

    @Override
    public TypeFines getByName(String name) {
        return  repository.findByName( name );
    }

    @Override
    public TypeFines editTypeFines(TypeFines typeFines) {
        return repository.saveAndFlush( typeFines );
    }

    @Override
    public List<TypeFines> getAll() {
        return repository.findAll();
    }
}
