package org.equinox.apirest.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.equinox.apirest.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsernameUniqueValidation implements ConstraintValidator<IsUserNameUnique, String> {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (userService != null) {
            return !userService.existsUsername(s);
        }
        return true;
    }
}
