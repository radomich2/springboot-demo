package com.opuscapita.demo.products.order;

import com.opuscapita.demo.products.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
