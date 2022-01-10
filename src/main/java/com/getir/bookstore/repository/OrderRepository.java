package com.getir.bookstore.repository;

import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.domain.Order;
import com.getir.bookstore.domain.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByCustomer(Customer customer, Pageable pageable);

    Page<Order> findAllByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT *\n" +
            "FROM  (SELECT To_char(created_date, 'MM')AS month,\n" +
            "              Sum(amount)                AS totalamount,\n" +
            "              Count(*)                   AS totalorder,\n" +
            "              Sum(total_quantity)        AS totalpurchasebookcount\n" +
            "       FROM   book_order\n" +
            "       WHERE  customer_id =:customerId\n" +
            "       GROUP  BY To_char(created_date, 'MM'))\n" +
            "ORDER  BY month")
    List<Statistic> getGroupByMonthlyReport(@Param("customerId") Long customerId);
}
