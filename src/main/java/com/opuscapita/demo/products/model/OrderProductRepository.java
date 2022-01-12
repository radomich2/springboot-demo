package com.opuscapita.demo.products.model;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {


    @Query(nativeQuery = true,value = "SELECT product_id as pid,sum(quantity) as totalQuantity FROM order_product op group by product_id order by totalQuantity desc limit 3;")
    List<ProjectQuantity> getBestSellers();
}
