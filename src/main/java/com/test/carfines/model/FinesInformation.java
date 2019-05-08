package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "FINES_INFORMATION")
public class FinesInformation {
    @Id
    @Column(name = "FINES_INFORMATION_ID")
    private long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "LICENSE_PLATE_NUMBER_ID")
    private LicensePlateNumber licensePlateNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TYPE_FINES_ID")
    private TypeFines typeFines;

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder( "FinesInformation = [ id = "+id+", " + licensePlateNumber+ ", " + typeFines +" ]" );
       return stringBuilder.toString();
    }

}
