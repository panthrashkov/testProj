package ru.rencredit.test.account.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rencredit.test.account.model.Account;
import ru.rencredit.test.person.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {

    private final EntityManager em;

    public AccountDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * @inheritDoc
     */
    public List<Account> getAccountFiltredList(Account account) {
        return loadByCriteria(Account account);
    }

    public List<Account> loadByCriteria(Account account) {
        CriteriaQuery<Account> criteria = buildCriteria(account.getPerson(), account.getName(),
                account.getBalance(), account.getCurrency());
        TypedQuery<Account> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private CriteriaQuery<Account> buildCriteria(Person person, String name, BigDecimal phoneAccount, String isActive) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Account> cq = builder.createQuery(Account.class);
        CriteriaBuilder qb = em.getCriteriaBuilder();

        Root<Person> personRoot = cq.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(qb.equal(personRoot.get("id"), person.getId()));

        Join<Person, Account> join = personRoot.join("accounts");

        if (name != null) {
            predicates.add(qb.equal(join.get("name"), name));
        }
        if (phoneAccount != null) {
            predicates.add(qb.equal(join.get("phoneAccount"), phoneAccount));
        }
        if (isActive != null) {
            predicates.add(qb.equal(join.get("isActive"), isActive));
        }
        cq.select(join).where(predicates.toArray(new Predicate[]{}));
        return cq;
    }

    /*
    получить офис по ID
    */
    @Override
    public Account findById(Long id) {
        Query query = em.createQuery("SELECT o FROM Account o WHERE o.id = :id");
        query.setParameter("id", id);
        return (Account)query.getSingleResult();
    }

    /*
    добавить счет в список
     */
    @Override
    @Transactional
    public void save(Account account) {
        em.persist(account);
    }

    /*
    получить список всех счето
     */


    @Override
    public void delete(Long id) {
        Query query = em.createQuery("SELECT o FROM Account o WHERE o.id = :id");
        query.setParameter("id", id);
        Account accountRemove = (Account)query.getSingleResult();
        em.remove(accountRemove);
    }
}
