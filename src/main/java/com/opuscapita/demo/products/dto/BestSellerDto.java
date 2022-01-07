package com.opuscapita.demo.products.dto;

import com.opuscapita.demo.products.model.Product;
import lombok.Data;

@Data
public class BestSellerDto {

    String productId;
    String quantity;
    String productName;

    public BestSellerDto(String pid, String name, String totalQuantity) {
        this.productId = pid;
        this.quantity = totalQuantity;
        this.productName = name;
    }
}
