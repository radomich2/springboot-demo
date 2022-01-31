package com.opuscapita.demo.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Wraps a list of some DTOs.
 */
public class ListDto<T> {

    private List<T> items = new ArrayList<>();

    public ListDto(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

}
