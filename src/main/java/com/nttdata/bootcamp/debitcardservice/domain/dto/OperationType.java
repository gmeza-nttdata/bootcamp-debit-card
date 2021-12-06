package com.nttdata.bootcamp.debitcardservice.domain.dto;

public enum OperationType {
    // For Account:
    DEPOSIT, WITHDRAWAL,
    // For Credit:
    CONSUMPTION, PAYMENT,
    // For Debit:
    DEBIT_CONSUMPTION
}
