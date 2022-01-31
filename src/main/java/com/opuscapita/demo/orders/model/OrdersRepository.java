package com.opuscapita.demo.orders.model;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Order, String> {

    @EntityGraph(attributePaths = {"items.product"})
    Optional<Order> findById(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @EntityGraph(attributePaths = {"items.product"})
    @Query("SELECT o FROM OrderEntity o WHERE o.id = ?1")
    Optional<Order> findByIdForUpdate(String id);
}
