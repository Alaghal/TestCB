package com.test.carfines.service;


import com.test.carfines.model.Owner;

import java.util.List;

public interface OwnerService {
    boolean addOwner (Owner owner);
    boolean delete(long id);
    Owner getByName(String name);
    boolean editOwner (Owner owner);
    List<Owner> getAll();
}
