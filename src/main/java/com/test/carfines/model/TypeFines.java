package com.test.carfines.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
