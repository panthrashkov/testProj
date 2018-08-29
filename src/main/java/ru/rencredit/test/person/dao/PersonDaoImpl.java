package ru.rencredit.test.person.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.rencredit.test.person.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    private final EntityManager em;

    @Autowired
    public PersonDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> getAll() {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person loadById(Long id) {
        return em.find(Person.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Person person) {
        em.persist(person);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Person person) {
        if (!em.contains(person)) {
            person = em.merge(person);
        }
        em.remove(person);
    }
}
