package com.opuscapita.demo.products.list;

import com.opuscapita.demo.dto.ListDto;

import java.util.List;

public class ProductsSummaryListDto extends ListDto<ProductSummaryDto> {
    public ProductsSummaryListDto(List<ProductSummaryDto> items) {
        super(items);
    }
}