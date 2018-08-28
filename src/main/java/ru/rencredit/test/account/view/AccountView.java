package ru.rencredit.test.account.view;


import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class AccountView {

    @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public BigDecimal balance;

    public String currency;


    public AccountView(Long id, String name, BigDecimal balance, String currency) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

}
