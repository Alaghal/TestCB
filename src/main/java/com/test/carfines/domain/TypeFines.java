package com.test.carfines.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TYPE_FINES")
public class TypeFines {
    @Id
    @Column(name = "TYPE_FINES_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "typeFines", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FinesInformation> finesInformations;

    @Column(name = "TYPE_FINES_NAME")
    private String nameTypeFines;

    @Column(name = "AMOUNT_DUTY" )
    private int amountDuty;

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder( "TypeFines = [ id"+id+", nameTypeFines = "+nameTypeFines+", "+" amountDuty = "+ amountDuty );
        if (finesInformations==null || finesInformations.isEmpty()) {
            stringBuilder.append( " }]" );
        } else {
            for (var finesInformation : finesInformations) {
                stringBuilder.append( " " + finesInformation.toString() + "," );
            }

            int lastComma = stringBuilder.length() - 1;
            stringBuilder.deleteCharAt( lastComma );
            stringBuilder.append( " }]" );
        }
        return stringBuilder.toString();
    }

}
