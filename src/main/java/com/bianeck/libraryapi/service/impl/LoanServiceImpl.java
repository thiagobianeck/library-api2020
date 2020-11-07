package com.bianeck.libraryapi.service.impl;

import com.bianeck.libraryapi.model.entity.Loan;
import com.bianeck.libraryapi.model.repository.LoanRepository;
import com.bianeck.libraryapi.service.LoanService;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository repository;

    public LoanServiceImpl(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {
        return repository.save(loan);
    }
}
