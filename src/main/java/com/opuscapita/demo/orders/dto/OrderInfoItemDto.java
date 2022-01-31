package com.opuscapita.demo.orders.dto;

import com.opuscapita.demo.orders.validation.ValidProductId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class OrderInfoItemDto {

    @NotNull
    @Min(1)
    @ValidProductId
    private Long productId;

    @NotNull
    @Min(1) @Max(1000)
    private int quantity;

    @Null(groups = OrderInfoDto.OnCreate.class)
    @NotNull(groups = OrderInfoDto.OnUpdate.class)
    @ApiModelProperty("For removing whole Product from Order")
    private Boolean toDelete;

    public boolean shouldBeDeleted() {
        return Boolean.TRUE.equals(toDelete);
    }
}
