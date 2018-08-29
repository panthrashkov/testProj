package ru.rencredit.test.person.dao;

import ru.rencredit.test.person.model.Person;

import java.util.List;


public interface PersonDao {

    /**
     * Получить клиента по id
     * @param id - значение параметра
     * @return Person модель клиента
     */
    Person loadById(Long id);
    /**
     * Сохранить нового клиента
     * @param person - модель клиента
     */
    void save(Person person);

    /**
     * Удалить клиента по ID
     * @param person - модель клиента
     */
    void delete(Person person);

    /**
     * Получить список всех клиентов
     * @return List<Person> список клиентов
     */
    List<Person> getAll();
}
