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
    @Override                                                                       //получить весь список организаций
    public List<Person> getAllPerson() {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();
    }

    /*
    получить организацию по имени
    */
    @Override
    public Person getPersonByName(String name, Long inn, Boolean isActive) {
        return loadByCriteria(name, inn, isActive);
    }

    @Override
    public Person loadByCriteria(String name, Long inn, Boolean isActive) {
        CriteriaQuery<Person> criteria = buildCriteria(name, inn, isActive);
        TypedQuery<Person> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    private CriteriaQuery<Person> buildCriteria(String name, Long inn, Boolean isActive) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> cq = builder.createQuery(Person.class);
        CriteriaBuilder qb = em.getCriteriaBuilder();

        Root<Person> personRoot = cq.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(qb.equal(personRoot.get("name"), name));
        if (inn != null) {
            predicates.add(qb.equal(personRoot.get("inn"), inn));
        }

        if (isActive != null) {
            predicates.add(qb.equal(personRoot.get("isActive"), isActive));
        }
        cq.select(personRoot).where(predicates.toArray(new Predicate[]{}));

        return cq;
    }


    @Override                                                                       //получить организацию по ID
    public Person loadById(Long id) {
            Query query = em.createQuery("SELECT o FROM Person o WHERE o.id = :id");
            query.setParameter("id", id);
            Person result1 = (Person)query.getSingleResult();
            return result1;
    }


    @Override                                                                       //добавление организаций
    public void save(Person person) {
        em.persist(person);
    }


    @Override
    public Person loadByIdCriteria(Long id) {
        CriteriaQuery<Person> criteria = buildCriteria(id);
        TypedQuery<Person> query1 = em.createQuery(criteria);
        return query1.getSingleResult();
    }
    private CriteriaQuery<Person> buildCriteria(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);

        Root<Person> person = criteria.from(Person.class);
        criteria.where(builder.equal(person.get("id"), id));

        return criteria;
    }

    @Override
    public void delete(Long id) {
        Query query = em.createQuery("SELECT o FROM Person o WHERE o.id = :id");
        query.setParameter("id", id);
        Person orgRemove = (Person)query.getSingleResult();
        em.remove(orgRemove);
    }
}
