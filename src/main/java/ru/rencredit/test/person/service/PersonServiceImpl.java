package ru.rencredit.test.person.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rencredit.test.exception.DateValidationException;
import ru.rencredit.test.person.dao.PersonDao;
import ru.rencredit.test.person.model.Person;
import ru.rencredit.test.person.view.PersonSave;
import ru.rencredit.test.person.view.PersonView;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.rencredit.test.person.view.PersonSave.MINIMAL_AGE;

/**
 * {@inheritDoc}
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {



    private  PersonDao personDao;

    public PersonServiceImpl() {
    }

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }


    /**
     * @inheritDoc
     */
    @Transactional(readOnly = true)
    @Override
    public PersonView loadById(Long id) {
        PersonView personView = new PersonView();
        try {
            Person person = personDao.loadById(id);
            personView.id = person.getId();
            personView.name = person.getName();
            personView.surname = person.getSurname();
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Организации с таким ID нет в базе данных");
        }
        return personView;
    }


     /**
     * @inheritDoc
     */
     @Transactional
    @Override
    public void update(PersonView person)  {

    }

    /**
     * @inheritDoc
     */
    @Transactional
    @Override
    public void add(PersonSave personSave) {
        validate(personSave);
        personDao.save(new Person(personSave.name, personSave.secondName, personSave.surname, personSave.birthDate ));
    }


    /**
     * @inheritDoc
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersonView> getAllPerson() {
        List<Person> all = personDao.getAllPerson();

        return all.stream()
                .map(mapPerson())
                .collect(Collectors.toList());
    }
    private Function<Person, PersonView> mapPerson() {
        return p -> {
            PersonView view = new PersonView();

            return view;
        };
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public void delete(Long id) {
        try {
            personDao.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Организации с таким ID нет в БД");
        }
    }

    private void validate(PersonSave personSave) {
        validateBirthDate(personSave.birthDate);
    }

    private void validateBirthDate(Date birthDate) {
        boolean isMinAgeAllowed = birthDate.before(convertToDate(LocalDate.now().minusYears(MINIMAL_AGE)));
        boolean isMaxAgeAllowed = birthDate.after(convertToDate(LocalDate.of( 1900, 1, 1)));
        if(!isMinAgeAllowed  || !isMaxAgeAllowed){
            String message;
            if(!isMinAgeAllowed) {
                 message = MessageFormat.format("Банк обслуживает клиентов старше {0} лет", MINIMAL_AGE);
            }else{
                 message = MessageFormat.format("Дата {0} не прошла валидацию", birthDate.toString());
            }
            log.error(message);
            throw new DateValidationException(message);
        }
    }

    private Date convertToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

}
