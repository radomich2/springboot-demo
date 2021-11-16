package com.opuscapita.demo.products.dto;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

//@JsonIncludeProperties({"id", "productName", "categoryName"})
public class ProductDto {
    private Long id;
    private Long version;
    private String productName;
    private String description;
    private String categoryName;
    private Long createdAt;
    private Long updatedAt;

    public ProductDto(Long id, Long version, String productName, String description, String categoryName, Long createdAt, Long updatedAt) {
        this.id = id;
        this.version = version;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }
}
