package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "OWNERS")
public class Owner {
    @Id
    @Column(name= "OWNER_ID")
    private long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LicensePlateNumber> licensePlateNumbers;

    @Column(name = "OWNER_NAME")
    private String nameOwner;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder( "Owner = [ id = "+ id+", nameOwner = "+nameOwner+", licensePlateNumbers = {" );
        for (var licensePlateNumber : licensePlateNumbers) {
            stringBuilder.append(" "+licensePlateNumber.toString() +"," );
        }

        int lastComma =  stringBuilder.length()-1;
        stringBuilder.deleteCharAt(lastComma );
        stringBuilder.append(" }]");

        return stringBuilder.toString();
    }

}
