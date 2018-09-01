package ru.rencredit.test.person.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.rencredit.test.person.dao.PersonDao;
import ru.rencredit.test.person.exception.DateValidationException;
import ru.rencredit.test.person.model.Person;
import ru.rencredit.test.person.view.PersonSave;
import ru.rencredit.test.person.view.PersonView;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ru.rencredit.test.person.view.PersonSave.MAXIMUM_AGE;
import static ru.rencredit.test.person.view.PersonSave.MINIMAL_AGE;
import static ru.rencredit.test.common.CommonValidation.checkPersonExistById;

/**
 * {@inheritDoc}
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;

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
        Person person = personDao.loadById(id);
        checkPersonExistById(id, person);
        return new PersonView(person.getId(), person.getName(), person.getSecondName(), person.getSurname(),
                person.getBirthDate());

    }


    /**
     * @inheritDoc
     */
    @Transactional
    @Override
    public void update(PersonView personView) {
        validate(personView);
        Person person = personDao.loadById(personView.getId());
        checkPersonExistById(personView.getId(), person);
        person.setName(personView.getName());
        person.setSurname(personView.getSurname());
        person.setBirthDate(personView.getBirthDate());
        if (!StringUtils.isEmpty(personView.getSecondName())) {
            person.setSecondName(personView.getSecondName());
        }

    }

    /**
     * @inheritDoc
     */
    @Transactional
    @Override
    public void add(PersonSave personSave) {
        validate(personSave);
        personDao.save(new Person(personSave.getName(), personSave.getSecondName(), personSave.getSurname(),
                personSave.getBirthDate()));
    }


    /**
     * @inheritDoc
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersonView> getAll() {
        return personDao.getAll()
                .stream()
                .map(p -> new PersonView(p.getId(), p.getName(), p.getSecondName(), p.getSurname(), p.getBirthDate()))
                .collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Person person = personDao.loadById(id);
        checkPersonExistById(id, person);
        personDao.delete(person);
    }

    private void validate(PersonSave personSave) {
        validateBirthDate(personSave.getBirthDate());
    }

    void validateBirthDate(Date birthDate) {
        boolean isMinAgeAllowed = birthDate.before(convertToDate(LocalDate.now().minusYears(MINIMAL_AGE)));
        boolean isMaxAgeAllowed = birthDate.after(convertToDate(LocalDate.now().minusYears(MAXIMUM_AGE)));
        if (!isMinAgeAllowed || !isMaxAgeAllowed) {
            String message;
            if (!isMinAgeAllowed) {
                message = MessageFormat.format("Банк обслуживает клиентов старше {0} лет", MINIMAL_AGE);
            } else {
                message = MessageFormat.format("Банк обслуживает клиентов младше {0} лет", MAXIMUM_AGE);
            }
            log.error(message);
            throw new DateValidationException(message);
        }
    }

    private Date convertToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }


}
