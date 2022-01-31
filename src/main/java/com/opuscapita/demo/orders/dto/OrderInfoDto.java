package com.opuscapita.demo.orders.dto;

import com.opuscapita.demo.orders.validation.ValidOrderInfoItems;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class OrderInfoDto {

    public interface OnCreate {}
    public interface OnUpdate {}

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    @Min(0)
    private Long version;

    @Size(min = 3, max = 512)
    private String description;

    @Valid
    @NotNull
    @Size(min = 1, max = 100)
    @ValidOrderInfoItems
    @ApiModelProperty("All items for new Order or changed-only items for updated Order")
    private List<OrderInfoItemDto> items;
}
