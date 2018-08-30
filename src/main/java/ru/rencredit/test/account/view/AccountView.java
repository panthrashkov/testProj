package ru.rencredit.test.account.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AccountView extends AccountSave {

    @NotNull(message = "Поле 'id' не может быть пустым")
    private Long id;

    public AccountView(Long id, String name, BigDecimal balance, String  currency) {
        super(name, balance, currency);
        this.id = id;
    }
}
