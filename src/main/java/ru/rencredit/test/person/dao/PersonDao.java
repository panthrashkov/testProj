package ru.rencredit.test.person.dao;

import ru.rencredit.test.person.model.Person;

import java.util.List;


public interface PersonDao {

    Person getPersonByName(String name, Long inn, Boolean isActive);        //получить организацию по имени
    Person loadByCriteria(String name, Long inn, Boolean isActive);

    Person loadById(Long id);           //получить организацию по ID

    Person loadByIdCriteria(Long id);           //получить организацию по ID (с использованием criteria)


    void save(Person person);       //добавить организацию в список


    List<Person> getAllPerson();    //получить весь список организаций


    void delete(Long id);                       //удалить организацию по ID



}
