package org.equinox.apirest.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.equinox.apirest.services.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueValidation implements ConstraintValidator<IsExistDB, String> {

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (productService == null) return true;
        return !productService.existsBySku(s);
    }
}
