package ru.rencredit.test.person.exception;

import ru.rencredit.test.common.BadRequestException;

public class DateValidationException extends BadRequestException {
    public DateValidationException(String message){
        super(message);
    }
}
