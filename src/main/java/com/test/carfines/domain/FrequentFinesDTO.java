package com.test.carfines.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FrequentFinesDTO {
    private long countFines;
    private TypeFines typeFines;
}
