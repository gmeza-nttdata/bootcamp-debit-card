package com.nttdata.bootcamp.debitcardservice.infrastructure.rest;

import com.nttdata.bootcamp.debitcardservice.application.DebitCardOperations;
import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import com.nttdata.bootcamp.debitcardservice.infrastructure.model.dto.OperationData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/debit-cards")
@RequiredArgsConstructor
public class DebitCardController {

    private final DebitCardOperations operations;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Flux<DebitCard>>> get() {
        return Mono.just(ResponseEntity.ok(operations.get()));
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<DebitCard>> getById(@PathVariable String id) {
        return Mono.just(id)
                .flatMap(operations::get)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<DebitCard>> createDebitCard(@RequestBody DebitCard debitCard) {
        return operations.createDebitCard(debitCard)
                .doOnError(throwable -> log.error(throwable.toString()))
                .map(ResponseEntity::ok)
                .onErrorResume(throwable ->
                        Mono.just(ResponseEntity.badRequest().body(new DebitCard(throwable.toString()))));
    }

    @PostMapping(value = "associate", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public Mono<ResponseEntity<DebitCard>> associateAccount(@RequestBody OperationData o) {
        return operations.associateAccount(o.getAccountNumber(), o.getCardId())
                .doOnError(throwable -> log.error(throwable.toString()))
                .map(ResponseEntity::ok)
                .onErrorResume(throwable ->
                        Mono.just(ResponseEntity.badRequest().body(new DebitCard(throwable.toString()))));
    }

    @PostMapping(value = "disassociate", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public Mono<ResponseEntity<DebitCard>> disassociateAccount(@RequestBody OperationData o) {
        return operations.disassociateAccount(o.getAccountNumber(), o.getCardId())
                .doOnError(throwable -> log.error(throwable.toString()))
                .map(ResponseEntity::ok)
                .onErrorResume(throwable ->
                        Mono.just(ResponseEntity.badRequest().body(new DebitCard(throwable.toString()))));
    }

    @DeleteMapping(value = "{cardId}")
    public Mono<ResponseEntity<Void>> deleteCreditCard(@PathVariable String cardId) {
        return operations.deleteCreditCard(cardId)
                .thenReturn(new
                        ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private ResponseEntity<DebitCard> postResponse(final DebitCard card) {
        return ResponseEntity
                .created(URI.create("/debit-cards/" + card.getId()))
                .body(card);
    }

}
