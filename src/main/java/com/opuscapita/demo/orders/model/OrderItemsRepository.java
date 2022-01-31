package com.opuscapita.demo.orders.model;

import com.opuscapita.demo.orders.reports.BestsellerDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT p.id AS productId, p.productName AS productName, SUM(oi.quantity) AS totalQty" +
        " FROM OrderItem oi JOIN oi.product p" +
        " GROUP BY productId, productName")
    List<BestsellerDto> getBestsellingProducts(Pageable pageable);
}
