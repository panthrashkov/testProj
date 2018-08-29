package ru.rencredit.test.person.model;



import ru.rencredit.test.account.model.Account;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * User
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name = "surname", length = 50, nullable = false)
    private String surname;

    @Column(name = "birth_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date birthDate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;

     public Person() {

    }

    public Person(String name, String secondName, String surname, Date birthDate) {
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getSurname() { return surname;}

    public void setSurname(String surname) {this.surname = surname;  }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public void addAccount(Account account) {
        getAccounts().add(account);
        account.setPerson(this);
    }

    public void removeAccount(Account account) {
        getAccounts().remove(account);
        account.setPerson(null);
    }
}
