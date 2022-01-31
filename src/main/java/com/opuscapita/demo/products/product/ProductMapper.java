package com.opuscapita.demo.products.product;

import com.opuscapita.demo.products.dto.ProductDto;
import com.opuscapita.demo.products.model.Product;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ProductMapper {

    public ProductDto map(Product product) {
        return new ProductDto(
            product.getId(),
            product.getVersion(),
            product.getProductName(),
            product.getDescription(),
            product.getPrice(),
            product.getCategory().getCategoryName(),
            instantToTimestamp(product.getCreatedAt()),
            instantToTimestamp(product.getUpdatedAt())
        );
    }

    private Long instantToTimestamp(Instant instant) {
        if (instant == null) {
            return null;
        }
        return instant.getEpochSecond();
    }
}
