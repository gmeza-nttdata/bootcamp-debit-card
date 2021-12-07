package com.nttdata.bootcamp.debitcardservice.application.repository;

import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardRepository {
    Flux<DebitCard> queryAll();
    Mono<DebitCard> findById(String id);
    Mono<DebitCard> create(DebitCard accountStatement);
    Mono<DebitCard> update(String id, DebitCard accountStatement);
    Mono<Void> delete(String id);
}
