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

    @Query(nativeQuery = true, value = "SELECT   *\n" +
            "FROM     (\n" +
            "                         SELECT          To_char(bo.created_date, 'MM')                                      AS month ,\n" +
            "                                         bo.amount                                                           AS totalamount,\n" +
            "                                         Count(*) over (partition BY to_char(bo.created_date, 'MM'))         AS totalorder,\n" +
            "                                         sum(io.quantity) over (partition BY to_char(bo.created_date, 'MM')) AS totalpurchasebookcount ,\n" +
            "                                         row_number() over(partition BY to_char(bo.created_date, 'MM') )        rownumber\n" +
            "                         FROM            book_order bo\n" +
            "                         LEFT OUTER JOIN book_order_item_orders boio\n" +
            "                         LEFT OUTER JOIN item_order io\n" +
            "                         WHERE           io.id=boio.item_orders_id\n" +
            "                         AND             boio.order_id=bo.id" +
            "                         AND             bo.customer_id= :customerId) t\n" +
            "WHERE    t.rownumber=1\n" +
            "ORDER BY t.month")
    List<Statistic> getGroupByMonthlyReport(@Param("customerId") Long customerId);
}
