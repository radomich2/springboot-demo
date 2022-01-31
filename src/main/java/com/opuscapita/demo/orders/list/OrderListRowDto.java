package com.opuscapita.demo.orders.list;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderListRowDto {
    private String id;
    private BigDecimal totalPrice;
    private String description;
}
