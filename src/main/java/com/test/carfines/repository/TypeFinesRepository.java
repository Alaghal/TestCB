package com.test.carfines.repository;

import com.test.carfines.model.TypeFines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeFinesRepository extends JpaRepository<TypeFines,Long> {
}
