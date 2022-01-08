package com.getir.bookstore.repository;

import com.getir.bookstore.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);

    Optional<Book> findByTitle(String title);
}
