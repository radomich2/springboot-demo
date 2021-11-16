package com.opuscapita.demo.products.list;

import com.opuscapita.demo.products.dto.ListPageableDto;
import com.opuscapita.demo.products.dto.ProductDto;

import java.util.List;

public class ProductsListPageableDto extends ListPageableDto<ProductDto> {

    public ProductsListPageableDto(List<ProductDto> items, long totalElements, int totalPages) {
        super(items, totalElements, totalPages);
    }
}
