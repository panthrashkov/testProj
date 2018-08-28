package ru.rencredit.test.account.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Account.class)
public abstract class Account_ {

    public static volatile SingularAttribute<Account, Integer> id;
    public static volatile SingularAttribute<Account, String> name;
    public static volatile SingularAttribute<Account, String> currency;
    public static volatile SingularAttribute<Account, BigDecimal> balance;

}
