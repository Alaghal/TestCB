package com.test.carfines.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "CAR_BRAND")
public class CarBrand {
    @Id
    @Column(name ="CAR_BRAND_ID" )
    private long id;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CarModel> carModels = new ArrayList<>();

    @Column(name = "CAR_BRAND_NAME")
    private String carBrandName;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("CarBrand [id=" + id + ", carBrandName = " + carBrandName);
        stringBuilder.append(", ListModel ={");
        for (var carModel:carModels) {
            stringBuilder.append(" "+carModel+",");
        }
        int lastComma =  stringBuilder.length()-1;
        stringBuilder.deleteCharAt(lastComma );
        stringBuilder.append(" }]");

        return stringBuilder.toString();
    }
}
