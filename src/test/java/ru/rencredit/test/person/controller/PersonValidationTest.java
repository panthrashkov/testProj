package ru.rencredit.test.person.controller;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.rencredit.test.person.view.PersonView;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonValidationTest {

    private static final int STRING_SIZE = 51;
    private final Long ID = 1L;
    private final String NAME = "name";
    private final String SECOND_NAME = "secondName";
    private final String SURNAME = "surname";
    private final Date BIRTH_DATE = new GregorianCalendar(100 + 1900, 0, 1).getTime();

    private static Validator validator;


    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validateOk() {
        PersonView person = new PersonView(ID, NAME, SECOND_NAME, SURNAME, BIRTH_DATE);
        assertTrue(validator.validate(person).isEmpty());
    }

    // ========================================= ID
    @Test
    public void validateIdNotNull() {
        PersonView person = new PersonView(null, NAME, SECOND_NAME, SURNAME, BIRTH_DATE);
        assertFalse(validate(person, "id", NotNull.class));
    }
    // ========================================= name
    @Test
    public void validateNameNotNull() {
        PersonView person = new PersonView(ID, null, SECOND_NAME, SURNAME, BIRTH_DATE);
        assertFalse(validate(person, "name", NotNull.class));
    }

    @Test
    public void validateNameByRegexp() {
        PersonView person = new PersonView(ID, "11", SECOND_NAME, SURNAME, BIRTH_DATE);
        assertFalse(validate(person, "name", Pattern.class));
    }

    @Test
    public void validateNameBySize() {
        PersonView person = new PersonView(ID, StringUtils.repeat("A", STRING_SIZE), SECOND_NAME, SURNAME, BIRTH_DATE);
        assertFalse(validate(person, "name", Size.class));
    }

    // ========================================= second name
    @Test
    public void validateSecondNameNull() {
        PersonView person = new PersonView(ID, NAME, null, SURNAME, BIRTH_DATE);
        assertTrue(validator.validate(person).isEmpty());
    }

    @Test
    public void validateSecondNameByRegexp() {
        PersonView person = new PersonView(ID, NAME, "1", SURNAME, BIRTH_DATE);
        assertFalse(validate(person, "secondName", Pattern.class));
    }

    @Test
    public void validateSecondNameBySize() {
        PersonView person = new PersonView(ID, NAME, StringUtils.repeat("A", STRING_SIZE), SURNAME, BIRTH_DATE);
        assertFalse(validate(person, "secondName", Size.class));
    }

    // ========================================= surname
    @Test
    public void validateSurnameNotNull() {
        PersonView person = new PersonView(ID, NAME, SECOND_NAME, null, BIRTH_DATE);
        assertFalse(validate(person, "surname", NotNull.class));
    }

    @Test
    public void validateSurnameByRegexp() {
        PersonView person = new PersonView(ID, NAME, SECOND_NAME, "11", BIRTH_DATE);
        assertFalse(validate(person, "surname", Pattern.class));
    }

    @Test
    public void validateSurnameBySize() {
        PersonView person = new PersonView(ID, NAME, SECOND_NAME, StringUtils.repeat("A", STRING_SIZE), BIRTH_DATE);
        assertFalse(validate(person, "surname", Size.class));
    }

    // ========================================= birthDate
    @Test
    public void validateBirthDateNotNull() {
        PersonView person = new PersonView(ID, NAME, SECOND_NAME, SURNAME, null);
        assertFalse(validate(person, "birthDate", NotNull.class));
    }


    private boolean validate(Object office, String field, Class annotation) {
        Set<ConstraintViolation<Object>> violations = validator.validate(office);
        return violations.stream()
                .noneMatch(
                        item -> item.getPropertyPath().toString().equals(field)
                                && item.getConstraintDescriptor().getAnnotation().annotationType().equals(annotation)
                );
    }

}