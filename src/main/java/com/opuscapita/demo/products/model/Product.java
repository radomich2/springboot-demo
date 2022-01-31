package com.opuscapita.demo.products.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Our internal representation of a Product model.
 * Do not share internal details over the REST API!
 */
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private long version;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column
    private String productName;

    @Column
    @Type(type="text")
    private String description;

    @Column
    private BigDecimal price;

    @Column
    @CreationTimestamp
    private Instant createdAt;

    @Column
    @UpdateTimestamp
    private Instant updatedAt;

    Product() { /* JPA only */ }

    public Product(String productName, String description, BigDecimal price, Category category) {
        update(productName, description, price, category);
    }

    public final void update(String productName, String description, BigDecimal price, Category category) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public Category getCategory() {
        return category;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
