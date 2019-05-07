package com.test.carfines.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "RECEIPT_PAYMENT_FINES")
public class ReceiptPaymentFines {
    @Id
    @Column(name = "RECEIPT_PAYMENT_FINES_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "receiptPaymentFines", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FinesInformation finesInformation;

    @Column(name = "RECEIPT_PAYMENT_FINES_STATUS_NAME")
    private  String statusFines;
}
