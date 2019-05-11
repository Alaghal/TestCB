package com.test.carfines.repository;

import com.test.carfines.domain.CarModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    @Query("select m from CarModel m where m.carModelName = :name")
    CarModel findByName(@Param("name") String carModelName);
}
