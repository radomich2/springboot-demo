package com.opuscapita.demo.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDto {
    private String id;
    private long version;
    private String description;
    private BigDecimal totalPrice;
    private List<OrderItemDto> items;
}
