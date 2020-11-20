package com.bianeck.libraryapi.model.repository;

import com.bianeck.libraryapi.model.entity.Book;
import com.bianeck.libraryapi.model.entity.Loan;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static com.bianeck.libraryapi.model.repository.BookRepositoryTest.createNewBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class LoanRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LoanRepository loanRepository;

    @Test
    @DisplayName("Deve verificar se existe empréstimo não devolvido para o livro ")
    public void existsByBookAndNotReturnedTest() {

        Loan persistLoan = createAndPersistLoan();

        boolean exists = loanRepository.existsByBookAndNotReturned(persistLoan.getBook());

        assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Deve buscar empréstimo pelo isbn do livro o customer.")
    public void findByBookIsbnOrCustomerTest() {
        Loan loan = createAndPersistLoan();

        Page<Loan> result = loanRepository.findByBookIsbnOrCustomer("123", "Fulano", PageRequest.of(0, 10));

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent()).contains(loan);
        assertThat(result.getPageable().getPageSize()).isEqualTo(10);
        assertThat(result.getPageable().getPageNumber()).isEqualTo(0);
        assertThat(result.getTotalElements()).isEqualTo(1);

    }

    public Loan createAndPersistLoan() {
        Book book = createNewBook("123");
        entityManager.persist(book);

        Loan loan = Loan.builder()
                .book(book)
                .customer("Fulano")
                .loanDate(LocalDate.now())
                .build();
        entityManager.persist(loan);

        return loan;
    }

}