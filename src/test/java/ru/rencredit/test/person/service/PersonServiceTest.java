package ru.rencredit.test.person.service;

import org.junit.Test;
import ru.rencredit.test.person.exception.DateValidationException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static ru.rencredit.test.person.view.PersonSave.MAXIMUM_AGE;
import static ru.rencredit.test.person.view.PersonSave.MINIMAL_AGE;

public class PersonServiceTest {

    private final Date VALID_DATE = convertToDate(LocalDate.now().minusYears(MINIMAL_AGE + 1));

    private final Date ERROR_DATE_LOW = new Date();
    private final Date ERROR_DATE_HIGH = convertToDate(LocalDate.now().minusYears(MAXIMUM_AGE + 1));

    private PersonServiceImpl personService = new PersonServiceImpl();


    @Test
    public void validateBirthDateValid(){
        personService.validateBirthDate(VALID_DATE);
    }

    @Test(expected = DateValidationException.class)
    public void validateBirthDateErrorLow(){
        personService.validateBirthDate(ERROR_DATE_LOW);
    }

    @Test(expected = DateValidationException.class)
    public void validateBirthDateErrorHigh(){
        personService.validateBirthDate(ERROR_DATE_HIGH);
    }


    private Date convertToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
