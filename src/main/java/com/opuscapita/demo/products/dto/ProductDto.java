package com.opuscapita.demo.products.dto;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private Long version;
    private String productName;
    private String description;
    private BigDecimal price;
    private String categoryName;
    private Long createdAt;
    private Long updatedAt;

    public ProductDto(Long id, Long version, String productName, String description, BigDecimal price, String categoryName, Long createdAt, Long updatedAt) {
        this.id = id;
        this.version = version;
        this.productName = productName;
        this.description = description;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
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
