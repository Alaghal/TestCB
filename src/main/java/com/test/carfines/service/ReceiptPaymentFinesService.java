package com.test.carfines.service;


import com.test.carfines.model.ReceiptPaymentFines;

import java.util.List;

public interface ReceiptPaymentFinesService {
    ReceiptPaymentFines addReceiptPaymentFines (ReceiptPaymentFines receiptPaymentFines);
    void delete(long id);
    ReceiptPaymentFines getByName(String name);
    ReceiptPaymentFines editReceiptPaymentFines (ReceiptPaymentFines receiptPaymentFines );
    List<ReceiptPaymentFines> getAll();
}
