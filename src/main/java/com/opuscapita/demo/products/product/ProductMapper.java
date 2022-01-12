package com.opuscapita.demo.products.product;

import com.opuscapita.demo.products.dto.ProductDTOList;
import com.opuscapita.demo.products.dto.ProductDto;
import com.opuscapita.demo.products.model.Product;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductMapper {

    public ProductDto map(Product product) {
        return new ProductDto(
            product.getProductId(),
            product.getVersion(),
            product.getProductName(),
            product.getDescription(),
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
