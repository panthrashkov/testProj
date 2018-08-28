package ru.rencredit.test.person.service;

import ru.rencredit.test.person.view.PersonSave;
import ru.rencredit.test.person.view.PersonView;

import java.util.List;

/**
 * Сервис
 */
public interface PersonService {

    /**
     * Получить клиента по id
     * @param id - значение параметра
     * @return PersonView представление клиента
     */
    PersonView loadById(Long id);

    /**
     * Обновить данные клиента
     * @param person - представление клиента
     */
    void update(PersonView person) ;

    /**
     * Сохранить нового клиента
     * @param person - представление клиента
     */
    void add(PersonSave person) ;

    /**
     * Получить список всех клиентов
     * @return List<PersonView> список клиентов
     */
    List<PersonView> getAllPerson();

    /**
     * Удалить клиента по ID
     * @param id - id клиента
     */
    void delete(Long id);

}