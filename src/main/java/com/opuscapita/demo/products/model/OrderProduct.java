package com.opuscapita.demo.products.model;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product=new Product();

    @ManyToOne
    @JoinColumn(name = "order_id")
    Orders oneOrders=new Orders();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOneOrders() {
        return oneOrders;
    }

    public void setOneOrders(Orders oneOrders) {
        this.oneOrders = oneOrders;
    }
}
