package ru.rencredit.test.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import ru.rencredit.test.person.exception.PersonNotFoundException;
import ru.rencredit.test.person.model.Person;

import java.text.MessageFormat;

@Slf4j
public class CommonValidation {

    public static final String REGEX_PATTERN_NAME = "^[a-zA-Z0-9а-яА-Я ,.\"]+$";

    public static void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
    }

    public static void checkPersonExistById(Long id, Person person) {
        if (person == null) {
            String message = MessageFormat.format("Клиента с id {0} нет в базе данных!", id);
            log.error(message);
            throw new PersonNotFoundException(message);
        }
    }
}
