package ru.rencredit.test.account.dao;

import org.springframework.stereotype.Repository;
import ru.rencredit.test.account.model.Account;
import ru.rencredit.test.account.model.Account_;
import ru.rencredit.test.person.model.Person_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        return loadByCriteria(account);
    }

    private List<Account> loadByCriteria(Account account) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> accountRoot = criteriaQuery.from(Account.class);

        Predicate predicate = criteriaBuilder.equal(accountRoot.get(Account_.person).get(Person_.id), account.getPerson().getId());
        if (account.getName() != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(accountRoot.get(Account_.name), "%" + account.getName() + "%"));
        }
        if (account.getCurrency() != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(accountRoot.get(Account_.currency), account.getCurrency()));
        }
        criteriaQuery.select(accountRoot).where(predicate);
        TypedQuery<Account> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Account findById(Long id) {
        return em.find(Account.class, id);
    }

    @Override
    public void save(Account account) {
        em.persist(account);
    }

    @Override
    public void delete(Account account) {
        if (!em.contains(account)) {
            account = em.merge(account);
        }
        em.remove(account);
    }
}
