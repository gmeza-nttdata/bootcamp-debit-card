package com.nttdata.bootcamp.debitcardservice.infrastructure.repository;

import com.nttdata.bootcamp.debitcardservice.application.repository.DebitCardRepository;
import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import com.nttdata.bootcamp.debitcardservice.infrastructure.model.dao.DebitCardDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DebitCardCrudRepository implements DebitCardRepository {

    @Autowired
    IDebitCardCrudRepository repository;


    @Override
    public Flux<DebitCard> queryAll() {
        return repository.findAll()
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<DebitCard> findById(String id) {
        return repository.findById(id)
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<DebitCard> create(DebitCard accountStatement) {
        return Mono.just(accountStatement)
                .doOnNext(s -> s.setId(null))
                .map(this::mapStatementToStatementDao)
                .flatMap(repository::save)
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<DebitCard> update(String id, DebitCard accountStatement) {
        return repository.findById(id)
                .flatMap(s -> {
                    s.setId(id);
                    return Mono.just(accountStatement)
                            .map(this::mapStatementToStatementDao);
                }).flatMap(repository::save)
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    private DebitCard mapStatementDaoToStatement(DebitCardDao accountStatementDao) {
        DebitCard accountStatement = new DebitCard();
        BeanUtils.copyProperties(accountStatementDao, accountStatement);
        return accountStatement;
    }

    private DebitCardDao mapStatementToStatementDao(DebitCard accountStatement) {
        DebitCardDao accountStatementDao = new DebitCardDao();
        BeanUtils.copyProperties(accountStatement, accountStatementDao);
        return accountStatementDao;
    }


}
