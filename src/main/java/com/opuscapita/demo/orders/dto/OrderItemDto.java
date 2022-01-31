package com.opuscapita.demo.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderItemDto {
    private long productId;
    private String productName;
    private BigDecimal unitPrice;
    private int quantity;
}
