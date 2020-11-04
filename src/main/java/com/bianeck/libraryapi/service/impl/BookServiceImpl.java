package com.bianeck.libraryapi.service.impl;

import com.bianeck.libraryapi.exception.BusinessException;
import com.bianeck.libraryapi.model.entity.Book;
import com.bianeck.libraryapi.model.repository.BookRepository;
import com.bianeck.libraryapi.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        if(repository.existsByIsbn(book.getIsbn())) {
            throw new BusinessException("Isbn já cadastrado.");
        }
        return repository.save(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }
}
