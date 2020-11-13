package com.bianeck.libraryapi.service.impl;

import com.bianeck.libraryapi.exception.BusinessException;
import com.bianeck.libraryapi.model.entity.Loan;
import com.bianeck.libraryapi.model.repository.LoanRepository;
import com.bianeck.libraryapi.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository repository;

    public LoanServiceImpl(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {
        if(repository.existsByBookAndNotReturned(loan.getBook())) {
            throw new BusinessException("Book already loaned");
        }
        return repository.save(loan);
    }

    @Override
    public Optional<Loan> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Loan update(Loan loan) {
        return null;
    }
}
