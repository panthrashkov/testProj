package ru.rencredit.test.person.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Person.class)
public abstract class Person_ {

    public static volatile SingularAttribute<Person, Long> id;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, String> secondName;
    public static volatile SingularAttribute<Person, String> surname;
    public static volatile SingularAttribute<Person, Date> birthday;

}
