package ru.rencredit.test.person.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PersonView extends PersonSave {

    @NotNull(message = "Поле 'id' не может быть пустым")
    private Long id;

    public PersonView(Long id, String name, String secondName, String surname, Date birthDate) {
        super(name, secondName, surname, birthDate);
        this.id = id;
    }
}
