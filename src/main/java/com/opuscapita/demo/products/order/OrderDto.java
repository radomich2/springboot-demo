package com.opuscapita.demo.products.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    Long OrderId;

    String orderDescription;

    String price;

}
