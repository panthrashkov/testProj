package ru.rencredit.test.person.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonView {

    public Long id;

    public String name;

    public String secondName;

    public Long surname;

    public Date birthDate;

}