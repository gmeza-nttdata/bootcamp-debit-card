package com.nttdata.bootcamp.debitcardservice.application;

import com.nttdata.bootcamp.debitcardservice.domain.DebitCardStatement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardStatementOperations {
    Flux<DebitCardStatement> queryAll();
    Mono<DebitCardStatement> findById(String id);
    Mono<DebitCardStatement> create(DebitCardStatement accountStatement);
    Mono<DebitCardStatement> update(String id, DebitCardStatement accountStatement);
    Mono<Void> delete(String id);

}
