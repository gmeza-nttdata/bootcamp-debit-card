package com.nttdata.bootcamp.debitcardservice.infrastructure.model.dto;

import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class DebitCardDto {

    private String id;
    private Long number;
    private String mainAccount;
    private BigDecimal mainAccountBalance;

    private LocalDate expirationDate;
    private String cvv;

    private ArrayList<String> accounts;
    private LocalDateTime creation;

    public DebitCardDto () {}

    public static DebitCardDto map(DebitCard debitCard, BigDecimal mainAccountBalance) {
        DebitCardDto dto = new DebitCardDto();
        BeanUtils.copyProperties(debitCard, dto);
        dto.mainAccountBalance = mainAccountBalance;
        return dto;
    }


}
