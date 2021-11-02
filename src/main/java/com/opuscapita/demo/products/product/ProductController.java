package com.opuscapita.demo.products.product;

import com.opuscapita.demo.error.ValidationErrorResponseDto;
import com.opuscapita.demo.products.model.ProductsRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    private ProductsRepository productsRepository;

    private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductsRepository productsRepository, ProductMapper productMapper) {
        this.productsRepository = productsRepository;
        this.productMapper = productMapper;
    }

    @PostMapping("/products")
    @ApiOperation(value = "Add new product", notes = "Validates input data, creates new product, returns all data of it.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "New product saved."),
        @ApiResponse(code = 400, message = "Input validation failed.", response = ValidationErrorResponseDto.class)
    })
    public ProductDto addProduct(@Valid @RequestBody ProductInfoDto productInfo) {
        var product = productsRepository.addProduct(productInfo);
        return productMapper.map(product);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable String id) {
        var product = productsRepository.getProduct(id);
        return productMapper.map(product);
    }

    @PatchMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable String id, @Valid @RequestBody ProductInfoDto productInfo) {
        var product = productsRepository.updateProduct(id, productInfo);
        return productMapper.map(product);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id) {
        productsRepository.deleteProduct(id);
    }
}
