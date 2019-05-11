package com.test.carfines.repository;


import com.test.carfines.domain.FinesInformation;
import com.test.carfines.domain.FrequentFinesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FinesInformationRepository extends JpaRepository<FinesInformation, Long> {

    @Query("select f from FinesInformation f " +
            "left join f.licensePlateNumber lp " +
            "left join lp.owner o " +
            "where o.nameOwner LIKE CONCAT('%',:ownerName,'%') and lp.licensePlateNumbersName LIKE CONCAT('%',:lpnName,'%')")
    List<FinesInformation> findFinesInformationByLPNAndOwnerName(@Param("ownerName") String ownerName,
                                                                 @Param("lpnName")   String lpnName);
    @Query("select f from FinesInformation f " +
            "left join f.licensePlateNumber lp " +
            "where  lp.licensePlateNumbersName LIKE CONCAT('%',:lpnName,'%')")
    List<FinesInformation> findFinesInformationByLicensePlateNumbersName( @Param("lpnName") String lpnName);

    @Query("select f from FinesInformation f " +
            "left join f.licensePlateNumber lp " +
            "left join lp.owner o " +
            "where o.nameOwner LIKE CONCAT('%',:ownerName,'%')")
    List<FinesInformation> findFinesInformationByOwnerName(@Param("ownerName") String ownerName);

    @Query("select new com.test.carfines.domain.FrequentFinesDTO( COUNT(f.id), f.typeFines) from FinesInformation f group by f.typeFines order by COUNT(f.id) desc ")
    List<FrequentFinesDTO> findFrequentFines();
}
