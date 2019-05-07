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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FINES_INFORMATION_ID", nullable = true)
    private FinesInformation finesInformation;

    @Column(name = "TYPE_FINES_NAME")
    private String nameTypeFines;
}
