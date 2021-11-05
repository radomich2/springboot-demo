package com.opuscapita.demo.products.list;

import com.opuscapita.demo.products.product.ProductDto;
import com.opuscapita.demo.products.product.ProductMapper;
import com.opuscapita.demo.products.model.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductListController {

    private ProductsRepository productsRepository;

    private ProductMapper productMapper;

    @Autowired
    public ProductListController(ProductsRepository productsRepository, ProductMapper productMapper) {
        this.productsRepository = productsRepository;
        this.productMapper = productMapper;
    }

    @GetMapping("/products")
    public ProductListDto getAllProducts() {
        List<ProductDto> list = productsRepository.getAllProducts()
            .stream()
            .map(productMapper::map)
            .collect(Collectors.toList());
        return new ProductListDto(list);
    }
}
