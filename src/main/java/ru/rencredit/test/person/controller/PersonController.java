package ru.rencredit.test.person.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rencredit.test.person.service.PersonService;
import ru.rencredit.test.person.view.PersonView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /*
    получить организацию по имени
    */

    @ApiOperation(value = "/api/person/list", nickname = "/api/person/list", httpMethod = "GET")
    @GetMapping("/api/person/list")
    public PersonViewList getPersonByName(@RequestParam String name,
                                              @RequestParam (value = "inn", required=false) Long inn,
                                              @RequestParam (value = "isActive", required=false) Boolean isActive) {
        return  personService.getPersonByName(name, inn, isActive);
    }

    /*
    получить организацию по ID
    */
    @ApiOperation(value = "api/person/{id}", nickname = "api/person/{id}", httpMethod = "GET")
    @GetMapping("/api/person/{id}")
    public PersonView loadById (@PathVariable Long id) {
        return personService.loadById(id);
    }

    /*
    обновить данные организации
    */
    @ApiOperation(value = "update", nickname = "update", httpMethod = "POST")
    @PostMapping("/api/person/update")
    public void update(@RequestBody PersonViewUpdate person) {
            personService.update(person);
    }

    /*
    добавить организацию
    */
    @ApiOperation(value = "api/person/save", nickname = "api/person/save", httpMethod = "POST")
    @PostMapping("/api/person/save")
    public void add( @RequestBody PersonViewSave person){
        personService.add(person);
    }


    /*
    получить весь список организаций
    */
    @ApiOperation(value = "getAllPerson", nickname = "getAllPerson", httpMethod = "GET")
    @GetMapping("/api/person/all")
    public List<PersonView> getAllPerson() { return personService.getAllPerson(); }

    /*
    удалить организацию по ID
    */
    @ApiOperation(value = "deletePerson", nickname = "deletePerson", httpMethod = "POST")
    @PostMapping("/api/person/delete/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }
}
