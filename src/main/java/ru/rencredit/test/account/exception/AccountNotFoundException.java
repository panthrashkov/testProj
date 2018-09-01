package ru.rencredit.test.account.exception;

import ru.rencredit.test.common.BadRequestException;

public class AccountNotFoundException extends BadRequestException {
    public AccountNotFoundException(String message){
        super(message);
    }
}
