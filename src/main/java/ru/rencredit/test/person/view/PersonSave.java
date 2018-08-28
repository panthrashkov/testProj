package ru.rencredit.test.person.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import ru.rencredit.test.utils.BindingResultValidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonSave {

    public static final int MINIMAL_AGE = 14;

    @NotNull(message = "Поле 'name' не может быть пустым")
    @Size(min = 1, max = 50, message = "Размер 'name' должен быть от 1 до 50 символов")
    @Pattern(regexp = BindingResultValidation.REGEX_PATTERN_NAME, message = "В имени присутствуют запрещеные символы")
    public String name;

    @Size(min = 1, max = 50, message = "Размер 'secondName' должен быть от 1 до 50 символов")
    @Pattern(regexp = BindingResultValidation.REGEX_PATTERN_NAME, message = "В отчестве присутствуют запрещеные символы")
    public String secondName;

    @NotNull(message = "Поле 'surname' не может быть пустым")
    @Size(min = 1, max = 50, message = "Размер 'surname' должен быть от 1 до 50 символов")
    @Pattern(regexp = BindingResultValidation.REGEX_PATTERN_NAME, message = "В фамилии присутствуют запрещеные символы")
    public String surname;

    @NotNull(message = "Поле 'birthDate' не может быть пустым")
    public Date birthDate;

}