package com.test.carfines.repository;


import com.test.carfines.model.FinesInformation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FinesInformationRepository extends JpaRepository<FinesInformation, Long> {

}
