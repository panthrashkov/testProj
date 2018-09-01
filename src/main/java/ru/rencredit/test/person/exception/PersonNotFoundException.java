package ru.rencredit.test.person.exception;

import ru.rencredit.test.common.BadRequestException;

public class PersonNotFoundException extends BadRequestException {
    public PersonNotFoundException(String message){
        super(message);
    }
}
