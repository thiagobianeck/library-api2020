package com.bianeck.libraryapi.service;

import com.bianeck.libraryapi.model.entity.Book;

import java.util.Optional;

public interface BookService {
    Book save(Book any);

    Optional<Book> getById(Long id);

    void delete(Book book);
}
