package org.equinox.apirest.validations;

import org.equinox.apirest.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null,"NotEmpty.product.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", null,"NotEmpty.product.description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", null, "NotNull.product.price");

        if (product.getPrice() == null) {
            errors.rejectValue("price", null,"NotNull.product.price");
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price", null,"Min.product.price");
        }
    }
}
