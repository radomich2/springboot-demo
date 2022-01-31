package com.opuscapita.demo.orders.validation;

import com.opuscapita.demo.products.model.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
public class ValidProductIdImpl implements ConstraintValidator<ValidProductId, Long> {

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext constraintValidatorContext) {
        if (productId == null) {
            return true;
        }
        return productsRepository.existsById(productId);
    }
}
