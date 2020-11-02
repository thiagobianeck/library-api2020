package com.bianeck.libraryapi.service.impl;

import com.bianeck.libraryapi.model.entity.Book;
import com.bianeck.libraryapi.model.repository.BookRepository;
import com.bianeck.libraryapi.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
