package ru.rencredit.test.utils;

import org.springframework.validation.BindingResult;

public class BindingResultValidation {

    public static final String REGEX_PATTERN_NAME = "^[a-zA-Z0-9а-яА-Я ,.\"]+$";

    public static void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
    }
}
