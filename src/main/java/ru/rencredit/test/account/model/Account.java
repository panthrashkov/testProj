package ru.rencredit.test.account.model;

import ru.rencredit.test.person.model.Person;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    public Account() {}

    public Account(String name, BigDecimal balance, String currency) {
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
