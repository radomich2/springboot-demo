package com.opuscapita.demo.products.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.opuscapita.demo.products.validation.ValidCategoryId;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductInfoDto {

    @NotNull
    @Size(min = 2, max = 128)
    private String productName;

    @NotNull
    @Size(min = 3, max = 512)
    private String description;

    @NotNull
    @Min(1)
    @ValidCategoryId
    private Long categoryId;

    @JsonCreator
    public ProductInfoDto(String productName, String description, Long categoryId) {
        this.productName = StringUtils.trimToNull(productName);
        this.description = StringUtils.trimToNull(description);
        this.categoryId  = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
