package com.test.carfines.repository;

import com.test.carfines.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
    @Query("select b from CarBrand b where b.carBrandName = :name")
    CarBrand findByName(@Param("name") String name);
}
