package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;


@Entity
@Data
@Table(name = "LICENSE_PLATE_NUMBER")
public class LicensePlateNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long licensePlateNumber_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FINES_INFORMATION_ID", nullable = false)
    private FinesInformation finesInformation;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "OWNERS_ID", nullable = false)
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CAR_BRAND_ID", nullable = false)
    private CarBrand carBrand;

}
