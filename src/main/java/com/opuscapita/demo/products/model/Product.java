package com.opuscapita.demo.products.model;

import java.time.Instant;

/**
 * Our internal representation of a Product model.
 * Do not share internal details over the REST API!
 */
public class Product {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    public Product(String id, String name, String description) {
        this.id = id;
        createdAt = Instant.now();
        update(name, description);
    }

    public Product(String name, String description) {
        updatedAt = Instant.now();
        update(name, description);
    }

    public final void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean isUpdated() {
        return updatedAt != null;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
