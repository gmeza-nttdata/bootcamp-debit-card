package com.nttdata.bootcamp.debitcardservice.infrastructure.model.dao;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Document("debit-card")
public class DebitCardDao {
    private String id;
    private Long number;
    private String mainAccount;
    private LocalDate expirationDate;
    private String cvv;
    private ArrayList<String> accounts;
    private LocalDateTime creation;

}
