package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TYPE_FINES")
public class TypeFines {
    @Id
    @Column(name = "TYPE_FINES_ID")
    private long id;

    @OneToOne(mappedBy = "typeFines", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FinesInformation finesInformation;

    @Column(name = "TYPE_FINES_NAME")
    private String nameTypeFines;

    @Column(name = "AMOUNT_DUTY" )
    private int amountDuty;

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder( "TypeFines = [ id"+id+", nameTypeFines = "+nameTypeFines+", "+" amountDuty = "+ amountDuty );
        return stringBuilder.toString();
    }

}
