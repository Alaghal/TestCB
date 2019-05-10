package com.test.carfines.repository;

import com.test.carfines.model.TypeFines;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TypeFinesRepository extends JpaRepository<TypeFines,Long> {
    @Query("select b from TypeFines b where b.nameTypeFines = :name")
    TypeFines findByName(@Param("name") String nameTypeFines);
}
