package ru.rencredit.test.person.view;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonView {

    public Long id;

    public String name;

    public String fullName;

    public Long inn;

    public Long kpp;

    public String urAddress;

    public Long phone;

    public Boolean isActive;

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn=" + inn +
                ", kpp=" + kpp +
                ", urAddress='" + urAddress + '\'' +
                ", phoneAccount=" + phone +
                ", isActive=" + isActive +
                '}';
    }
}