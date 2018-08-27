package ru.rencredit.test.person.service;

import ru.rencredit.test.person.view.PersonView;

import java.util.List;

/**
 * Сервис
 */
public interface PersonService {

    /*
    получить организацию по имени
    */
    PersonViewList getPersonByName(String name, Long inn, Boolean isActive);

    /*
    получить организацию по ID
    */
    PersonView loadById(Long id);

    /*
    обновить данные организации
    */
    void update(PersonViewUpdate person) ;

    /*
    добавить организацию
    */
    void add(PersonViewSave person) ;

    /*
    получить весь список организаций
    */
    List<PersonView> getAllPerson();

    /*
    удалить организацию по ID
    */
    void delete(Long id);

}