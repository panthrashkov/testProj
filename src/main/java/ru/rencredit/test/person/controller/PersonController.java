package ru.rencredit.test.person.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rencredit.test.person.service.PersonService;
import ru.rencredit.test.person.view.PersonView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/person", produces = APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Получить список всех клиентов
     * @return List<PersonView> список клиентов
     */
    @ApiOperation(value = "api/person/all", nickname = "getAllPerson", httpMethod = "GET")
    @GetMapping("/all")
    public List<PersonView> getAllPerson() { return personService.getAllPerson(); }

    /**
     * Получить клиента по id
     * @param id - значение параметра
     * @return PersonView представление клиента
     */
    @ApiOperation(value = "api/person/{id}", nickname = "getPersonById", httpMethod = "GET")
    @GetMapping("/{id}")
    public PersonView loadById (@PathVariable Long id) {
        return personService.loadById(id);
    }

    /**
     * Обновить данные клиента
     * @param person - представление клиента
     */
    @ApiOperation(value = "/api/person/update", nickname = "update", httpMethod = "POST")
    @PostMapping("/api/person/update")
    public void update(@RequestBody PersonView person) {
            personService.update(person);
    }

    /**
     * Сохранить нового клиента
     * @param person - представление клиента
     */
    @ApiOperation(value = "api/person/save", nickname = "save", httpMethod = "POST")
    @PostMapping("/api/person/save")
    public void add( @RequestBody PersonView person){
        personService.add(person);
    }

    /**
     * Удалить клиента по ID
     * @param id - id клиента
     */
    @ApiOperation(value = "deletePerson", nickname = "deletePerson", httpMethod = "POST")
    @PostMapping("/api/person/delete/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }
}
