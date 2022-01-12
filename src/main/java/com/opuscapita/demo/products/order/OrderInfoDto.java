package com.opuscapita.demo.products.order;


import lombok.Data;

import java.io.Serializable;

@Data
public class OrderInfoDto implements Serializable {

    public String id;

    public String orderDescription;

    public String price;

    public ProductDetails productDetails;

    public OrderInfoDto() {
    }

    public OrderInfoDto(String orderDescription, ProductDetails productDetails) {
        this.orderDescription = orderDescription;
        this.productDetails = productDetails;
    }

}
