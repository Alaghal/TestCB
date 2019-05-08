package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;


@Entity
@Data
@Table(name = "LICENSE_PLATE_NUMBER")
public class LicensePlateNumber {
    @Id
    @Column(name = "LICENSE_PLATE_NUMBER_ID")
    private long id;

    @OneToOne(mappedBy = "licensePlateNumber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FinesInformation finesInformation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_MODEL_ID")
    private CarModel carModel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    @Override
    public String toString() {
        StringBuilder  stringBuilder = new StringBuilder( "LicensePlateNumber =[ id"+id+", " +carModel+ ", "+owner+ " ]");
        return stringBuilder.toString();
    }

}
