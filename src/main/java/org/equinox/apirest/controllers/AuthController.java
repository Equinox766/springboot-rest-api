package org.equinox.apirest.controllers;

import jakarta.validation.Valid;
import org.equinox.apirest.entities.User;
import org.equinox.apirest.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(originPatterns = "*")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        user.setAdmin(Boolean.FALSE);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), "El campo "+ fieldError.getField() + " " + fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
