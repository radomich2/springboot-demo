package com.opuscapita.demo.config;

import com.opuscapita.demo.products.model.CategoriesRepository;
import com.opuscapita.demo.products.model.ProductsRepository;
import com.opuscapita.demo.products.product.ProductService;
import com.opuscapita.demo.products.product.ProductServiceWithJpa;
import com.opuscapita.demo.products.product.ProductServiceWithRepositories;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class BeansConfig {

    public static final String PRODUCT_SERVICE_REPOSITORIES = "PRODUCT_SERVICE_REPOSITORIES";
    public static final String PRODUCT_SERVICE_JPA = "PRODUCT_SERVICE_JPA";

    @Bean
    public RandomStringGenerator getRandomStringGenerator() {
        return new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
    }

    @Bean(PRODUCT_SERVICE_REPOSITORIES)
    public ProductService productServiceWithRepositories(ProductsRepository productsRepo, CategoriesRepository categoriesRepo) {
        return new ProductServiceWithRepositories(productsRepo, categoriesRepo);
    }

    @Bean(PRODUCT_SERVICE_JPA)
    public ProductService productServiceWithJpa(EntityManager em) {
        return new ProductServiceWithJpa(em);
    }

}
