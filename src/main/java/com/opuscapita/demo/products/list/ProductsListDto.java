package com.opuscapita.demo.products.list;

import com.opuscapita.demo.dto.ListDto;
import com.opuscapita.demo.products.dto.ProductDto;

import java.util.List;

public class ProductsListDto extends ListDto<ProductDto> {
    public ProductsListDto(List<ProductDto> items) {
        super(items);
    }
}