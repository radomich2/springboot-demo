package com.opuscapita.demo.products.list;

import com.opuscapita.demo.products.product.ProductDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapps a list of ProductDto.
 */
public class ProductListDto {

    private List<ProductDto> products = new ArrayList<>();

    public ProductListDto(List<ProductDto> products) {
        this.products = products;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

}
