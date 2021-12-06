package com.nttdata.bootcamp.debitcardservice.application.impl;

import com.nttdata.bootcamp.debitcardservice.application.DebitCardOperations;
import com.nttdata.bootcamp.debitcardservice.application.repository.StatementRepository;
import com.nttdata.bootcamp.debitcardservice.application.service.AccountService;
import com.nttdata.bootcamp.debitcardservice.application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebitCardOperationsImpl implements DebitCardOperations {
    private final StatementRepository repository;
    private final AccountService accountService;
    private final UserService userService;



}
