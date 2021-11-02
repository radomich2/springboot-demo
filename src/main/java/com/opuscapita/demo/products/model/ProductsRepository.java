package com.opuscapita.demo.products.model;

import com.opuscapita.demo.config.AppSettings;
import com.opuscapita.demo.products.product.ProductInfoDto;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Not thread safe! Only for trivial example.
 */
@Service
public class ProductsRepository {

    private static final int PRODUCT_ID_LENGTH = 12;

    private RandomStringGenerator idGenerator;

    private AppSettings appSettings;

    private Map<String, Product> products = new HashMap<>();

    @Autowired
    public ProductsRepository(RandomStringGenerator idGenerator, AppSettings appSettings) {
        this.idGenerator = idGenerator;
        this.appSettings = appSettings;
    }

    public List<Product> getAllProducts() {
        return products.values().stream().collect(Collectors.toList());
    }

    public Product addProduct(ProductInfoDto productInfo) {
        String id = idGenerator.generate(PRODUCT_ID_LENGTH);
        var product = new Product(id, productInfo.getName(), productInfo.getDescription());
        products.put(id, product);

        return product;
    }

    public Product updateProduct(String id, ProductInfoDto productInfo) {
        ensureProductExists(id);
        Product product = products.get(id);
        product.update(
            productInfo.getName(),
            productInfo.getDescription()
        );
        return product;
    }

    public Product deleteProduct(String id) {
        ensureProductExists(id);
        return products.remove(id);
    }

    public Product getProduct(String id) {
        ensureProductExists(id);
        return products.get(id);
    }

    private void ensureProductExists(String id) {
        if (!products.containsKey(id)) {
            throw new RuntimeException("Product #"+id+" not found!");
        }
    }

}
