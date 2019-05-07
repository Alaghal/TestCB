package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.lang.model.element.Name;
import javax.persistence.*;

@Entity
@Data
@Table(name = "CAR_MODEL")
public class CarModel {
    @Id
    @Column(name="CAR_MODEL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "LICENSE_PLATE_NUMBER_ID", nullable = true)
    private LicensePlateNumber licensePlateNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "carBrand_ID", nullable = false)
    private CarBrand carBrand;

    @Column(name = "CAR_MODEL_NAME")
    private String carModelName;



}
