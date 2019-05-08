package com.test.carfines.service;

import com.test.carfines.model.TypeFines;

import java.util.List;

public interface TypeFinesService {
    TypeFines addTypeFines(TypeFines typeFines);
    void delete(long id);
    TypeFines getByName(String name);
    TypeFines editTypeFines (TypeFines typeFines );
    List<TypeFines> getAll();
}
