package com.opuscapita.demo.products.product;

import com.opuscapita.demo.products.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductDto map(Product product) {
        return new ProductDto(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getCreatedAt().getEpochSecond(),
            product.isUpdated() ? product.getUpdatedAt().getEpochSecond() : null
        );
    }
}
