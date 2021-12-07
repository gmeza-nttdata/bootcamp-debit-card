package com.nttdata.bootcamp.debitcardservice.application;

import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardOperations {

    Flux<DebitCard> get();
    Mono<DebitCard> get(String id);
    Mono<DebitCard> createDebitCard(DebitCard debitCard);
    Mono<DebitCard> associateAccount(String accountNumber, String cardId);
    Mono<DebitCard> disassociateAccount(String accountNumber, String cardId);
    Mono<Void> deleteCreditCard(String cardId);

}
