package com.test.carfines.repository;

import com.test.carfines.model.LicensePlateNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicensePlateNumberRepository extends JpaRepository<LicensePlateNumber,Long> {
}
