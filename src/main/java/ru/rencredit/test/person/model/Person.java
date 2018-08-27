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
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "full_Name")
    private String surname;

    @Column(name = "birthday")
    private Date birthday;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;

    public void addAccount(Account account) {
        getAccounts().add(account);
        account.setPerson(this);
    }

    public void removeAccount(Account account) {
        getAccounts().remove(account);
        account.setPerson(null);
    }

    /**
     * Конструктор для hibernate
     */
    public Person() {

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

    public Person(Long id, Integer version, String name, String surname,  Date birthday) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

}
