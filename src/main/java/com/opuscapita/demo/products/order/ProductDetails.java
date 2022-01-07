package com.opuscapita.demo.products.order;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDetails implements Serializable {
    public List<ProductDetail> productDetail;
}