package com.opuscapita.demo.products.dto;

import com.opuscapita.demo.dto.ListDto;

import java.util.List;

public class ListPageableDto<T> extends ListDto {

    private long totalElements;

    private int totalPages;

    public ListPageableDto(List<T> products, long totalElements, int totalPages) {
        super(products);
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
