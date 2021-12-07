package com.nttdata.bootcamp.debitcardservice.infrastructure.model.dto;

import lombok.Data;

@Data
public class OperationData {
    private String accountNumber;
    private String cardId;
}
