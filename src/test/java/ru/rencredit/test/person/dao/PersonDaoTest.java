package ru.rencredit.test.person.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.rencredit.test.TestConfig;
import ru.rencredit.test.person.model.Person;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@Transactional
@DirtiesContext
@TestPropertySource(locations = "classpath:application-test.properties")
public class PersonDaoTest {

    private final String NAME = "name";
    private final String SURNAME = "surname";
    private final Date BIRTH_DATE = new GregorianCalendar(100 + 1900, 0, 1).getTime();
    private final Long BAD_ID = -1L;

    @Autowired
    private PersonDao personDao;

    private Long personId;

    @Before
    public void  setUp(){
        Person person = new Person(NAME, null, SURNAME, BIRTH_DATE);
        personDao.save(person);
        personId = person.getId();
    }

    @Test
    public void loadById(){
        Person person = personDao.loadById(personId);
        assertTrue(person != null);
        assertEquals(NAME, person.getName());
        assertEquals(SURNAME, person.getSurname());
        assertEquals(BIRTH_DATE, person.getBirthDate());
    }

    @Test
    public void loadByIdNull(){
        Person person = personDao.loadById(BAD_ID);
        assertTrue(person == null);
    }

    @Test
    public void  getAll(){
        List<Person> persons = personDao.getAll();
        assertTrue(persons != null && persons.size() >= 1 );
    }

    @Test()
    public void delete(){
        personDao.delete(personDao.loadById(personId));
        assertTrue(personDao.loadById(personId) == null);
    }



}