package com.nttdata.bootcamp.debitcardservice.infrastructure.mockrepository;

import com.nttdata.bootcamp.debitcardservice.application.repository.DebitCardRepository;
import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;

public class MockDebitCardRepository implements DebitCardRepository {
    @Override
    public Flux<DebitCard> queryAll() {

        return Flux.fromIterable(new ArrayList<>()
        {{
            LocalDate ld = LocalDate.now().plusYears(4);
            add(new DebitCard("1"));
            add(new DebitCard("2",12345678L,"mainAccount1", ld, "123"));
            add(new DebitCard("3",12345679L,"mainAccount2", ld, "456"));
            add(new DebitCard("4",72352628L,"mainAccount3", ld, "789"));
            add(new DebitCard("5"));
        }});
    }

    @Override
    public Mono<DebitCard> findById(String id) {
        return Mono.just(new DebitCard("4",72352628L,"mainAccount3",
                LocalDate.now().plusYears(4), "789"));
    }

    @Override
    public Mono<DebitCard> create(DebitCard accountStatement) {
        return Mono.just(new DebitCard("6"));
    }

    @Override
    public Mono<DebitCard> update(String id, DebitCard accountStatement) {
        return Mono.just(new DebitCard(id,12345679L,"mainAccount2", LocalDate.now().plusYears(4), "456"));
    }

    @Override
    public Mono<Void> delete(String id) {
        return Mono.empty();
    }
}
