package com.opuscapita.demo.products.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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

//    @ManyToMany(mappedBy = "productSet")
//    Set<Orders> orderSet = new HashSet<>();

    @OneToMany(mappedBy = "product")
    Set<OrderProduct> orderProducts=new HashSet<>();


    @Column
    private String productName;

    @Column
    @Type(type = "text")
    private String description;

    @Column
    @CreationTimestamp
    private Instant createdAt;

    @Column
    @UpdateTimestamp
    private Instant updatedAt;

    Product() { /* JPA only */ }

    public Product(String productName, String description, Category category) {
        update(productName, description, category);
    }

    public final void update(String productName, String description, Category category) {
        this.productName = productName;
        this.description = description;
        this.category = category;
    }

    public Long getProductId() {
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
