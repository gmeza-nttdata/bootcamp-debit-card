package com.nttdata.bootcamp.debitcardservice.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class DebitCard {
    private String id;
    private Long number;
    private String mainAccount;

    private LocalDate expirationDate;
    private String cvv;

    private ArrayList<String> accounts;
    private LocalDateTime creation;

    public DebitCard() {}

    public DebitCard(String id) {
        this.id  = id;
    }
    
    public void verifyAccounts() throws IllegalArgumentException {
        this.setCreation(LocalDateTime.now());
        
        if (this.getMainAccount() == null || this.getMainAccount().isEmpty())
            throw new IllegalArgumentException("Main account must not be null or empty");
        
        if (this.getAccounts() == null || this.getAccounts().isEmpty()) {
            this.setAccounts(new ArrayList<>());
            this.getAccounts().add(this.getMainAccount());
        } else if (!this.getAccounts().contains(this.getMainAccount())) {
            this.getAccounts().add(this.getMainAccount());
        }
    }

}
