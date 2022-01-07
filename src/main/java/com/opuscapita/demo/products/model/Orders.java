package com.opuscapita.demo.products.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(name = "description",columnDefinition = "TEXT")
    private String orderDecription;

    @Column
    private Integer price;


    //    @ManyToMany(cascade=CascadeType.MERGE)
//    @JoinTable(
//        name = "order_product",
//        joinColumns = @JoinColumn(name = "order_id"),
//        inverseJoinColumns = @JoinColumn(name = "product_id"))
//        Set<Product> productSet=new HashSet<>();

    @OneToMany(mappedBy = "oneOrders", fetch = FetchType.EAGER
        , cascade = CascadeType.ALL,orphanRemoval=true)
    Set<OrderProduct> orderProducts = new HashSet<>();

    public String getOrderDecription() {
        return orderDecription;
    }

    public void setOrderDecription(String orderDecription) {
        this.orderDecription = orderDecription;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}

