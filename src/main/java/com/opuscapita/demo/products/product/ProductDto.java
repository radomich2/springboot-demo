package com.opuscapita.demo.products.product;

public class ProductDto {
    private String id;
    private String name;
    private String description;
    private Long createdAt;
    private Long updatedAt;

    public ProductDto(String id, String name, String description, Long createdAt, Long updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }
}
