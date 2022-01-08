package com.getir.bookstore.repository;

import com.getir.bookstore.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findAllByFirstNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<Customer> findByUserName(String userName);
}
