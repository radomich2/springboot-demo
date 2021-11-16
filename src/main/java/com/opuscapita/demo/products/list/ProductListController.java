package com.opuscapita.demo.products.list;

import com.opuscapita.demo.products.model.Category;
import com.opuscapita.demo.products.model.Product;
import com.opuscapita.demo.products.model.ProductsRepository;
import com.opuscapita.demo.products.dto.ProductDto;
import com.opuscapita.demo.products.product.ProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductListController {

    private ProductsRepository repository;

    private ProductMapper productMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    public ProductListController(ProductsRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;
    }

    @GetMapping("/products-query")
    public ProductsListDto getAllProductsQuery() {
        // TODO: named query, join categories, ordering, searching, max-results
        String jpQuery = "SELECT p FROM Product p JOIN p.category";
        List<ProductDto> list = em.createQuery(jpQuery, Product.class)
            .getResultList()
            .stream()
            .map(productMapper::map)
            .collect(Collectors.toList());

        return new ProductsListDto(list);
    }

    @GetMapping("/products-criteria-api")
    public ProductsListDto getAllProductsCriteriaApi(
        @RequestParam(required = false) String search,
        @RequestParam(required = false) String order) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        // Select form products
        Root<Product> product = cq.from(Product.class);
        // Join categories
        Join<Product, Category> category = (Join<Product, Category>) product.<Product, Category>fetch("category", JoinType.INNER);

        // Optional filter by text found in either product name or category name
        if (StringUtils.isNotBlank(search)) {
            String pattern = "%" + search + "%";
            Predicate inProductName = cb.like(product.get("productName"), pattern);
            Predicate inCategoryName = cb.like(category.get("categoryName"), pattern);
            cq.where( cb.or(inProductName, inCategoryName) );
        }
        // Ordering
        if ("product".equals(order)) {
            cq.orderBy( cb.asc(product.get("productName")) );
        } else if ("category".equals(order)) {
            cq.orderBy( cb.asc(category.get("categoryName")) );
        } else {
            cq.orderBy( cb.desc(product.get("id")) );
        }

        List<ProductDto> list = em.createQuery(cq).getResultList()
            .stream()
            .map(productMapper::map)
            .collect(Collectors.toList());

        return new ProductsListDto(list);
    }

    @GetMapping("/products-repository")
    public ProductsListDto getAllProductsRepository() {
        // TODO: getAllWithCategories, Sort.by
        List<ProductDto> list = repository.findAll()
            .stream()
            .map(productMapper::map)
            .collect(Collectors.toList());

        return new ProductsListDto(list);
    }

    @GetMapping("/products-repository-dynamic")
    public ProductsListDto getAllProductsRepositoryDynamic(
        @RequestParam(required = true) String search,
        @RequestParam(required = true) List<Long> categories
    ) {

        String productNamePattern = "%" + search + "%";
        List<ProductDto> list = repository.findAllByProductNameLikeAndCategoryIdInOrderByIdDesc(
                productNamePattern, categories)
            .stream()
            .map(productMapper::map)
            .collect(Collectors.toList());

        return new ProductsListDto(list);
    }

    @GetMapping("/products-pageable")
    public ProductsListPageableDto getAllProductsPageable(
        @RequestParam(defaultValue = "0", required = false) int page,
        @RequestParam(defaultValue = "3", required = false) int size) {

        Pageable pageReq = PageRequest.of(page, size, Sort.by("id").descending());
        Page<ProductDto> resultPage = repository.findAll(pageReq)
            .map(productMapper::map);
        resultPage.getTotalElements();
        resultPage.getTotalPages();

        return new ProductsListPageableDto(
            resultPage.getContent(),
            resultPage.getTotalElements(),
            resultPage.getTotalPages()
        );
    }

    @GetMapping("/products-summary")
    public ProductsSummaryListDto getAllProductsSummary() {
        List<ProductSummaryDto> results = repository.getProductsAsSummary();
        return new ProductsSummaryListDto(results);
    }

}
