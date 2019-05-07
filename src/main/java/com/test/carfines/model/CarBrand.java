package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "CAR_BRAND")
public class CarBrand {
    @Id
    @Column(name ="CAR_BRAND_ID" )
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @OneToOne( mappedBy = "carBrand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LicensePlateNumber licensePlateNumber;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CarModel> carModels = new ArrayList<>();

    @Column(name = "CAR_BRAND_NAME")
    private String carBrandName;
}
