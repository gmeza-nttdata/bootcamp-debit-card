package com.nttdata.bootcamp.debitcardservice.application.impl;

import com.nttdata.bootcamp.debitcardservice.application.DebitCardOperations;
import com.nttdata.bootcamp.debitcardservice.application.repository.DebitCardRepository;
import com.nttdata.bootcamp.debitcardservice.application.service.AccountService;
import com.nttdata.bootcamp.debitcardservice.application.service.UserService;
import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import com.nttdata.bootcamp.debitcardservice.domain.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebitCardOperationsImpl implements DebitCardOperations {
    private final DebitCardRepository repository;
    private final AccountService accountService;

    @Override
    public Flux<DebitCard> get() {
        return repository.queryAll();
    }

    @Override
    public Mono<DebitCard> get(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<DebitCard> createDebitCard(DebitCard debitCard) {

        try {
            debitCard.verifyAccounts();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return Mono.error(e);
        }

        return Flux.fromIterable(debitCard.getAccounts())
                .flatMap(accountService::get)
                .doOnError(throwable -> log.error(throwable.toString()))
                .onErrorResume(throwable -> Mono.error(new IllegalArgumentException("Invalid account: " + throwable.toString())))
                .collectList()
                .thenReturn(debitCard)
                .flatMap(repository::create);
    }

    @Override
    public Mono<DebitCard> associateAccount(String accountNumber, String cardId) {

        Mono<DebitCard> debitCardMono = repository.findById(cardId);
        Mono<Account> mainAccountMono = debitCardMono
                .map(DebitCard::getMainAccount)
                .flatMap(accountService::get);

        return accountService.get(accountNumber)
                .zipWith(mainAccountMono)
                .flatMap(tuple2 -> {
                    if (!tuple2.getT1().getUserId().equals(tuple2.getT2().getUserId()))
                        return Mono.error(new IllegalArgumentException("Account must be from same User"));
                    return debitCardMono.flatMap(debitCard -> {
                        if (debitCard.getAccounts().contains(accountNumber))
                            return Mono.error(new IllegalArgumentException("Account already associated"));
                        debitCard.getAccounts().add(accountNumber);
                        return repository.update(cardId, debitCard);
                    });
                });
    }

    @Override
    public Mono<DebitCard> disassociateAccount(String accountNumber, String cardId) {

        Mono<DebitCard> debitCardMono = repository.findById(cardId);

        return debitCardMono.flatMap(debitCard -> {
            if (debitCard.getAccounts().size() <= 1)
                return Mono.error(new IllegalArgumentException("At least one account must be associated to this debit card"));
            if (!debitCard.getAccounts().contains(accountNumber))
                return Mono.error(new IllegalArgumentException("This account is not associated with this card"));

            debitCard.getAccounts().remove(accountNumber);
            if (accountNumber.equals(debitCard.getMainAccount()))
                debitCard.setMainAccount(debitCard.getAccounts().get(0));
            return repository.update(cardId, debitCard);
        });
    }

    @Override
    public Mono<Void> deleteCreditCard(String cardId) {
        return repository.delete(cardId);
    }
}
