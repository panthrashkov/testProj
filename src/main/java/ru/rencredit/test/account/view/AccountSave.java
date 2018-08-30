package ru.rencredit.test.account.view;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rencredit.test.common.CommonValidation;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountSave {

    @NotNull(message = "Поле 'name' не может быть пустым")
    @Size(min = 1, max = 50, message = "Размер 'name' должен быть от 1 до 50 символов")
    @Pattern(regexp = CommonValidation.REGEX_PATTERN_NAME, message = "В имени присутствуют запрещеные символы")
    private String name;

    @NotNull(message = "Поле 'balance' не может быть пустым")
    @Digits(integer=19, fraction=4, message="Некорректный формат поля balance")
    private BigDecimal balance;

    @NotNull(message = "Поле 'currency' не может быть пустым")
    @Size(min = 1, max = 4, message = "Размер 'currency' должен быть от 1 до 4 символов")
    @Pattern(regexp = CommonValidation.REGEX_PATTERN_NAME, message = "В поле валюта присутствуют запрещеные символы")
    private  String currency;

}
