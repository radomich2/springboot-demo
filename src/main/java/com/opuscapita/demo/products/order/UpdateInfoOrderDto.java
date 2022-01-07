package com.opuscapita.demo.products.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateInfoOrderDto extends OrderInfoDto {

    public Boolean IsAdd;

    public Boolean IsRemoved;

    public Boolean IsPricedAltered;

    public Boolean IsQuantityAltered;

}
