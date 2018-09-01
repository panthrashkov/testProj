package ru.rencredit.test.person.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rencredit.test.common.CommonValidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonSave {

    public static final int MINIMAL_AGE = 14;
    public static final int MAXIMUM_AGE = 90;

    @NotNull(message = "Поле 'name' не может быть пустым")
    @Size(min = 1, max = 50, message = "Размер 'name' должен быть от 1 до 50 символов")
    @Pattern(regexp = CommonValidation.REGEX_PATTERN_NAME, message = "В имени присутствуют запрещеные символы")
    private String name;

    @Size(min = 1, max = 50, message = "Размер 'secondName' должен быть от 1 до 50 символов")
    @Pattern(regexp = CommonValidation.REGEX_PATTERN_NAME, message = "В отчестве присутствуют запрещеные символы")
    private String secondName;

    @NotNull(message = "Поле 'surname' не может быть пустым")
    @Size(min = 1, max = 50, message = "Размер 'surname' должен быть от 1 до 50 символов")
    @Pattern(regexp = CommonValidation.REGEX_PATTERN_NAME, message = "В фамилии присутствуют запрещеные символы")
    private String surname;

    @NotNull(message = "Поле 'birthDate' не может быть пустым")
    private Date birthDate;

}