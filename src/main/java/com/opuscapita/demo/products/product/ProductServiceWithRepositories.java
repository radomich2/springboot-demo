package com.opuscapita.demo.products.product;

import com.opuscapita.demo.error.ApiNotFoundError;
import com.opuscapita.demo.products.model.CategoriesRepository;
import com.opuscapita.demo.products.model.Category;
import com.opuscapita.demo.products.model.Product;
import com.opuscapita.demo.products.model.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ProductServiceWithRepositories implements ProductService {

    private ProductsRepository productsRepo;
    private CategoriesRepository categoriesRepo;

    @Autowired
    public ProductServiceWithRepositories(ProductsRepository productsRepo, CategoriesRepository categoriesRepo) {
        this.productsRepo = productsRepo;
        this.categoriesRepo = categoriesRepo;
    }

    @Override
    @Transactional
    public Product addProduct(ProductInfoDto productInfo) {
        Category category = findCategory(productInfo.getCategoryId());
        Product product = new Product(productInfo.getProductName(), productInfo.getDescription(), productInfo.getPrice(), category);
        productsRepo.save(product);
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
        productsRepo.delete(product);
        return product;
    }

    @Override
    public Product getProduct(Long id) {
        return findProduct(id);
    }

    private Product findProduct(Long id) {
        return productsRepo.findById(id)
            .orElseThrow(() -> new ApiNotFoundError("Product #" + id + " not found!"));
    }

    private Category findCategory(Long id) {
        return categoriesRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Unexpected category id!"));
    }
}
