package com.nttdata.bootcamp.debitcardservice.application;

import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import com.nttdata.bootcamp.debitcardservice.infrastructure.model.dto.DebitCardDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardOperations {

    Flux<DebitCard> get();
    Mono<DebitCardDto> get(String id);
    Mono<DebitCard> createDebitCard(DebitCard debitCard);
    Mono<DebitCard> associateAccount(String accountNumber, String cardId);
    Mono<DebitCard> disassociateAccount(String accountNumber, String cardId);
    Mono<Void> deleteCreditCard(String cardId);

}
