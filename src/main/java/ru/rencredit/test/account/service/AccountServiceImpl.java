package ru.rencredit.test.account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rencredit.test.account.dao.AccountDao;
import ru.rencredit.test.account.model.Account;
import ru.rencredit.test.account.view.AccountView;
import ru.rencredit.test.person.dao.PersonDao;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    private final PersonDao personDao;


    public AccountServiceImpl(AccountDao accountDao, PersonDao personDao) {
        this.accountDao = accountDao;
        this.personDao = personDao;
    }

    @Override
    public List<AccountView> getAccountByOrgId(AccountView accountView) {
        Account account = validate(accountView);
        return accountDao.getAccountFiltredList(account).stream()
                .map(mapAccount())
                .collect(Collectors.toList());
    }

    @Override
    public AccountView findById(Long id) {
        return null;
    }

    @Override
    public void update(AccountView accountView) {
        Account account = validate(accountView);
        accountDao.update(account);
    }

    @Override
    public void add(Long personId, AccountView accountView) {
        Account account = validate(accountView);
    }

    @Override
    public void delete(Long id) {

    }

    private Account validate(AccountView accountView) {
        return null;
    }

    private Function<Account, AccountView> mapAccount() {
        return p -> new AccountView(p.getId(), p.getName(), p.getBalance(), p.getCurrency());
    }
}

