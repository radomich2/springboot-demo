package com.opuscapita.demo.orders.validation;

import com.opuscapita.demo.orders.dto.OrderInfoItemDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * List of items should contain unique products.
 */
public class ValidOrderInfoItemsImpl implements ConstraintValidator<ValidOrderInfoItems, List<OrderInfoItemDto>> {

    @Override
    public boolean isValid(List<OrderInfoItemDto> items, ConstraintValidatorContext constraintValidatorContext) {
        if (items == null || items.isEmpty()) {
            return true;
        }
        long uniqueProductsCount = items.stream().map(OrderInfoItemDto::getProductId).distinct().count();
        return (uniqueProductsCount == items.size());
    }
}
