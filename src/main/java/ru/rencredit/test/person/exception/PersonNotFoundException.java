package ru.rencredit.test.person.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message){
        super(message);
    }
}
