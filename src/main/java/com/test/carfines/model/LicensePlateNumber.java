package com.test.carfines.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LICENSE_PLATE_NUMBER")
public class LicensePlateNumber {
    @Id
    @Column(name = "LICENSE_PLATE_NUMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "licensePlateNumber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FinesInformation> finesInformations = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_MODEL_ID")
    private CarModel carModel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder( "LicensePlateNumber =[ id" + id + ", " + carModel + ", " + owner );
        stringBuilder.append( ", finesInformations ={" );

        if (finesInformations == null || finesInformations.isEmpty()) {
            stringBuilder.append( " }]" );
        } else {
            for (var finesInformation : finesInformations) {
                stringBuilder.append( " " + finesInformation + "," );
            }

            int lastComma = stringBuilder.length() - 1;
            stringBuilder.deleteCharAt( lastComma );
            stringBuilder.append( " }]" );
        }
        return stringBuilder.toString();
    }

}
