package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "FINES_INFORMATION")
public class FinesInformation {
    @Id
    @Column(name = "FINES_INFORMATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "RECEIPT_PAYMENT_FINES_ID", nullable = true)
    private ReceiptPaymentFines receiptPaymentFines;

    @OneToOne(mappedBy = "finesInformation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LicensePlateNumber licensePlateNumber;

    @OneToOne(mappedBy = "finesInformation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TypeFines typeAccruedFine;



}
