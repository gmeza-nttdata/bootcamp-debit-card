package com.nttdata.bootcamp.debitcardservice.infrastructure.repository;

import com.nttdata.bootcamp.debitcardservice.infrastructure.model.dao.DebitCardStatementDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IStatementCrudRepository extends ReactiveCrudRepository<DebitCardStatementDao, String> {
}
