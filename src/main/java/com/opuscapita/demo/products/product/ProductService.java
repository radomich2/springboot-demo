package com.opuscapita.demo.products.product;

import com.opuscapita.demo.products.model.Product;

public interface ProductService {

    Product addProduct(ProductInfoDto productInfo);

    Product updateProduct(Long id, ProductInfoDto productInfo);

    Product deleteProduct(Long id);

    Product getProduct(Long id);
}
