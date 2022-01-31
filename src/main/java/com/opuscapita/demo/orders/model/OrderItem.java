package com.opuscapita.demo.orders.model;

import com.opuscapita.demo.products.model.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderItem {

    /**
     * Composite primary key.
     */
    @Embeddable @EqualsAndHashCode @Data
    public static class Pk implements Serializable {
        @Column(name = "order_id", nullable = false)
        private String orderId;

        @Column(name = "product_id", nullable = false)
        private Long productId;
    }

    @EmbeddedId
    private Pk id = new Pk();

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private int quantity;

    public OrderItem(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.unitPrice = product.getPrice();
        this.quantity = quantity;
    }

    public void delete() {
        order = null;
    }

    public void update(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
