package com.nttdata.bootcamp.debitcardservice.infrastructure.repository;

import com.nttdata.bootcamp.debitcardservice.infrastructure.model.dao.DebitCardDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IDebitCardCrudRepository extends ReactiveCrudRepository<DebitCardDao, String> {
}
