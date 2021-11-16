package com.opuscapita.demo.products.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String categoryName;

    Category() { /* for JPA */ }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
