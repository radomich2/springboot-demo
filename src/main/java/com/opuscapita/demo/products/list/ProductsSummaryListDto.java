package com.opuscapita.demo.products.list;

import com.opuscapita.demo.products.dto.ListDto;
import com.opuscapita.demo.products.dto.ProductDto;

import java.util.List;

public class ProductsSummaryListDto extends ListDto<ProductSummaryDto> {
    public ProductsSummaryListDto(List<ProductSummaryDto> items) {
        super(items);
    }
}