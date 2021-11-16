package com.opuscapita.demo.products.validation;

import com.opuscapita.demo.products.model.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
public class ValidCategoryIdImpl implements ConstraintValidator<ValidCategoryId, Long> {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public boolean isValid(Long categoryId, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryId == null) {
            return true;
        }
        return categoriesRepository.existsById(categoryId);
    }
}
