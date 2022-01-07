package com.opuscapita.demo.products.order;


import com.opuscapita.demo.products.model.Orders;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public OrderDto map(Orders order) {
        return new OrderDto(
            order.getOrderId(),
            order.getOrderDecription(),
            String.valueOf(order.getPrice())
        );


    }
}
