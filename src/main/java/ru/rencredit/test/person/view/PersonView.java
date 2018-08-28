package ru.rencredit.test.person.view;

import javax.validation.constraints.NotNull;

public class PersonView extends PersonSave {

    @NotNull(message = "Поле 'id' не может быть пустым")
    public Long id;
}
