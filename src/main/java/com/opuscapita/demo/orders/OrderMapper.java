package com.opuscapita.demo.orders;

import com.opuscapita.demo.orders.dto.OrderDto;
import com.opuscapita.demo.orders.dto.OrderItemDto;
import com.opuscapita.demo.orders.model.Order;
import com.opuscapita.demo.orders.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public OrderDto map(Order order) {
        List<OrderItemDto> items = order.getItems().stream().map(this::mapItem).collect(Collectors.toList());
        return new OrderDto(order.getId(), order.getVersion(), order.getDescription(), order.getTotalPrice(), items);
    }

    private OrderItemDto mapItem(OrderItem item) {
        return new OrderItemDto(
            item.getProduct().getId(),
            item.getProduct().getProductName(),
            item.getUnitPrice(),
            item.getQuantity());
    }
}
