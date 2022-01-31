package com.opuscapita.demo.demodata;

import com.opuscapita.demo.products.model.CategoriesRepository;
import com.opuscapita.demo.products.model.Category;
import com.opuscapita.demo.products.model.Product;
import com.opuscapita.demo.products.model.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Load demo data when application is launching.
 */
@Component
@Profile("demodata & !prod")
public class DemoDataLoader {

    private static final Logger LOG = LoggerFactory.getLogger(DemoDataLoader.class);

    @Autowired
    private CategoriesRepository categoriesRepo;

    @Autowired
    private ProductsRepository productsRepo;

    @PostConstruct
    @Transactional
    void loadDemoData() {

        LOG.debug("Check...");
        if (categoriesRepo.count() > 0) {
            LOG.info("Found some categories - demo data loading skipped.");
            return;
        }

        LOG.info("Categories...");
        List<Category> categories = new ArrayList<>();
        Stream.of("Category A", "Category B")
            .forEach(catName -> {
                Category cat = new Category(catName);
                categoriesRepo.save(cat);
                categories.add(cat);
            });

        LOG.info("Products...");
        Stream.of("Product A|10", "Product B|20")
            .forEach(line -> {
                String[] tokens = line.split("\\|");
                categories.forEach(cat -> {
                    String prodName = tokens[0] + " cat." + cat.getId();
                    String description = "Description of " + prodName;
                    BigDecimal price = new BigDecimal(tokens[1]);
                    Product product = new Product(prodName, description, price, cat);
                    productsRepo.save(product);
                });
            });
    }
}
