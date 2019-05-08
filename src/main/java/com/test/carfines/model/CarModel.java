package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.lang.model.element.Name;
import javax.persistence.*;

@Entity
@Data
@Table(name = "CAR_MODEL")
public class CarModel {
    @Id
    @Column(name="CAR_MODEL_ID")
    private long id;

    @OneToOne(mappedBy = "carModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LicensePlateNumber licensePlateNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CAR_BRAND_ID")
    private CarBrand carBrand;

    @Column(name = "CAR_MODEL_NAME")
    private String carModelName;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("CarModel [id=" + id + ", carModelName = " + carModelName+" ]");
        return stringBuilder.toString();
    }

}
