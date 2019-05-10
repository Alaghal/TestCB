package com.test.carfines.repository;

import com.test.carfines.model.CarBrand;
import com.test.carfines.model.LicensePlateNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LicensePlateNumberRepository extends JpaRepository<LicensePlateNumber,Long> {
    @Query("select b from LicensePlateNumber b where b.carModel.id = :idCarModel and b.owner.id = :idOwner")
    List<LicensePlateNumber> findByIdCarModelAndIdOwner(@Param("idCarModel") long idCarModel,
                                                       @Param("idOwner") long idOwner);

}
