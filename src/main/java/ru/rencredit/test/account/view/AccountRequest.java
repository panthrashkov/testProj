package ru.rencredit.test.account.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotNull(message = "Поле 'personId' не может быть пустым")
    private Long personId;

    private String name;

    private BigDecimal balance;

    private  String currency;
}

