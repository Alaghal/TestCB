package com.test.carfines.repository;

import com.test.carfines.domain.Owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    @Query("select b from Owner b where b.nameOwner = :name")
    Owner findByName(@Param("name") String ownerName);
}
