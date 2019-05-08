package com.test.carfines.service;


import com.test.carfines.model.Owner;

import java.util.List;

public interface OwnerService {
    Owner addOwner (Owner owner);
    void delete(long id);
    Owner getByName(String name);
    Owner editOwner (Owner owner);
    List<Owner> getAll();
}
