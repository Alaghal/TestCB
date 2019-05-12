package com.test.carfines.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @OneToMany(mappedBy = "licensePlateNumber", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FinesInformation> finesInformations = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CAR_MODEL_ID")
    private CarModel carModel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    @Pattern(regexp = "^[A-Z0-9]{0,9}$|^[А-Я0-9]{0,9}$",message = "Только буквы вырхнего регистра, цифры , длина номера < 9 символов")
    @Column(name = "LICENSE_PLATE_NUMBER_NAME")
    private String licensePlateNumbersName;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder( "LicensePlateNumber =[ id" + id + ", " +licensePlateNumbersName+", "+ carModel + ", " + owner );
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
