package com.test.carfines.service;

import com.test.carfines.domain.TypeFines;

import java.util.List;

public interface TypeFinesService {
    boolean addTypeFines(TypeFines typeFines);
    boolean delete(long id);
    TypeFines getByName(String name);
    boolean editTypeFines (TypeFines typeFines );
    List<TypeFines> getAll();
}
