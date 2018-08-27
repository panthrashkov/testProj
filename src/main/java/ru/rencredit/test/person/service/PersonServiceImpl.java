package ru.rencredit.test.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rencredit.test.person.dao.PersonDao;
import ru.rencredit.test.person.model.Person;
import ru.rencredit.test.person.view.PersonView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@Transactional
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
    @Override
    public PersonView loadById(Long id) {
        PersonView personView = new PersonView();
        try {
            Person person = personDao.loadById(id);
            personView.id = person.getId();
            personView.name = person.getName();
            personView.fullName = person.getSurname();
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Организации с таким ID нет в базе данных");
        }
        return personView;
    }


     /**
     * @inheritDoc
     */
    @Override
    public void update(PersonView person)  {
        if (person.id == null ) {
            throw new OrganisationValidationException("Поле ID является обязательным параметром");
        }
        if (person.name == null) {
            throw new OrganisationValidationException("Поле name является обязательным параметром");
        }
        if (person.fullName == null) {
            throw new OrganisationValidationException("Поле fullName является обязательным параметром");
        }
        if (person.inn == null) {
            throw new OrganisationValidationException("Поле inn является обязательным параметром");
        }
        if (person.kpp == null) {
            throw new OrganisationValidationException("Поле kpp является обязательным параметром");
        }
        if (person.urAddress == null) {
            throw new OrganisationValidationException("Поле urAddress является обязательным параметром");
        }
        Person org;
        try {
            org = personDao.loadById(person.id);
        } catch (EmptyResultDataAccessException e) {
            throw new OrgOutException("Организации с таким ID нет в базе данных");
        }
        org.setName(person.name);
        org.setSurname(person.fullName);
        validate(person.inn);
        org.setInn(validate(person.inn));
        validateKppNumberLength(person.kpp);
        org.setKpp(validateKppNumberLength(person.kpp));
        org.setUrAddress(person.urAddress);
        if (person.phone == null && person.isActive == null){
            person.phone = phoneToString(org.getPhone());
            person.isActive = org.getIsActive();
        } else if (person.phone == null) {
            person.phone = phoneToString(org.getPhone());
        } else if (person.isActive == null) {
            person.isActive = org.getIsActive();
        }
        org.setPhone(phoneToLong(person.phone));
        org.setActive(person.isActive);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void add(PersonView person) {

        if (person.name == null) {
            throw new OrganisationValidationException("Поле name является обязательным параметром");
        }
        if (person.fullName == null) {
            throw new OrganisationValidationException("Поле fullName является обязательным параметром");
        }
        if (person.inn == null) {
            throw new OrganisationValidationException("Поле inn является обязательным параметром");
        }
        if (person.kpp == null) {
            throw new OrganisationValidationException("Поле kpp является обязательным параметром");
        }
        if (person.urAddress == null) {
            throw new OrganisationValidationException("Поле urAddress является обязательным параметром");
        }
        Person ogr;
        validate(person.inn);
        validateKppNumberLength(person.kpp);
        if (person.phone == null) {
            ogr = new Person  (person.name, person.fullName, validate(person.inn),
                                    validateKppNumberLength(person.kpp),  person.urAddress,  person.isActive);
        }
        else {
            ogr = new Person (person.name, person.fullName, validate(person.inn),
                                    validateKppNumberLength(person.kpp),  person.urAddress,
                                    phoneToLong(person.phone), person.isActive);
        }
        personDao.save(ogr);
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
            view.id = Long.valueOf(p.getId());
            view.name = p.getName();
            view.fullName = p.getSurname();
            view.inn = p.getInn();
            view.kpp = p.getKpp();
            view.urAddress = p.getUrAddress();
            view.phone = p.getPhone();
            view.isActive = p.getIsActive();
            return view;
        };
    }

    /**
     * @inheritDoc
     */
    @Override
    public void delete(Long id) {
        try {
            personDao.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Организации с таким ID нет в БД");
        }
    }

    private Long validate(String a) {
        Long aLong;
        try {
            aLong = Long.parseLong(a);
        } catch (NumberFormatException e) {
            throw new RuntimeException("ИНН должен состоять из 10 цифр");
        }
        int count = 1;
        Long b = aLong / 10;
        while (b >= 1){
            count++;
            b /= 10;
        }
        if (count != 10) {
            throw new RuntimeException("ИНН должен состоять из 10 цифр");
        }
        return aLong;
    }

    private Long validateKppNumberLength(String a) {
        Long aLong = null;
        try {
            aLong = Long.parseLong(a);
        } catch (NumberFormatException e) {
            throw new RuntimeException("КПП должен состоять из 9 цифр");
        }
        int count = 1;
        Long b = aLong / 10;
        while (b >= 1){
            count++;
            b /= 10;
        }
        if (count != 9) {
            throw new RuntimeException("КПП должен состоять из 9 цифр");
        }
        return aLong;
    }

    private Long phoneToLong (String phone) {
        try {
            Long a = Long.parseLong(phone);
            return a;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Телефон должен состоять из цифр");
        }
    }

    private String phoneToString (Long phone) {
        return phone.toString();
    }
}
