package com.opuscapita.demo.orders.model;

import com.opuscapita.demo.products.model.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity(name = "OrderEntity")
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class Order {
    @Id
    private String id;

    @Version
    private long version;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column
    @CreationTimestamp
    private Instant createdAt;

    @Column
    @UpdateTimestamp
    private Instant updatedAt;

    public Order(String id) {
        this.id = id;
    }

    public OrderItem addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(this, product, quantity);
        items.add(item);
        return item;
    }

    public void removeItem(OrderItem item) {
        item.delete();
        items.remove(item);
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void forceUpdatedAtChange() {
        updatedAt = Instant.now();
    }

    public void recalculateTotalPrice() {
        totalPrice = items.stream().map(i -> i.getTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Long, OrderItem> getItemsMappedByProductId() {
        return items.stream()
            .collect(Collectors.toMap(i -> i.getProduct().getId(), Function.identity()));
    }

}
