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
    @Column(name= "OWNERS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LicensePlateNumber> licensePlateNumber;

    @Column(name = "OWNER_NAME")
    private String nameOwner;
}
