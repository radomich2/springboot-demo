package com.opuscapita.demo.products.product;

import com.opuscapita.demo.error.ApiNotFoundError;
import com.opuscapita.demo.products.model.Category;
import com.opuscapita.demo.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class ProductServiceWithJpa implements ProductService {

    private EntityManager em;

    @Autowired
    public ProductServiceWithJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public Product addProduct(ProductInfoDto productInfo) {
        Category category = findCategory(productInfo.getCategoryId());
        Product product = new Product(productInfo.getProductName(), productInfo.getDescription(), productInfo.getPrice(), category);
        em.persist(product);
        return product;
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductInfoDto productInfo) {
        Product product = findProduct(id);
        Category category = findCategory(productInfo.getCategoryId());
        product.update(
            productInfo.getProductName(),
            productInfo.getDescription(),
            productInfo.getPrice(),
            category
        );
        return product;
    }

    @Override
    @Transactional
    public Product deleteProduct(Long id) {
        Product product = findProduct(id);
        em.remove(product);
        return product;
    }

    @Override
    public Product getProduct(Long id) {
        return findProduct(id);
    }

    private Product findProduct(Long id) {
        Product product = em.find(Product.class, id);
        if (product == null) {
            throw new ApiNotFoundError("Product #" + id + " not found!");
        }
        return product;
    }

    private Category findCategory(Long id) {
        Category category = em.find(Category.class, id);
        if (category == null) {
            throw new IllegalArgumentException("Category #" + id + " not found!");
        }
        return category;
    }
}
