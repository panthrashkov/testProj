package ru.rencredit.test.account.view;


import io.swagger.annotations.ApiModelProperty;

public class AccountView {

    @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public boolean isActive;


    public AccountView(Long id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "AccountView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
