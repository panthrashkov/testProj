package ru.rencredit.test.account.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rencredit.test.account.dao.AccountDao;
import ru.rencredit.test.account.exception.AccountNotFoundException;
import ru.rencredit.test.account.model.Account;
import ru.rencredit.test.account.view.AccountRequest;
import ru.rencredit.test.account.view.AccountSave;
import ru.rencredit.test.account.view.AccountView;
import ru.rencredit.test.person.dao.PersonDao;
import ru.rencredit.test.person.model.Person;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.rencredit.test.utils.CommonValidation.checkPersonExistById;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    private final PersonDao personDao;


    public AccountServiceImpl(AccountDao accountDao, PersonDao personDao) {
        this.accountDao = accountDao;
        this.personDao = personDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountView> getAccountByOrgId(AccountRequest accountRequest) {
        Account account = new Account(accountRequest.getName(), accountRequest.getBalance(),
                accountRequest.getCurrency());
        Person person = personDao.loadById(accountRequest.getPersonId());
        checkPersonExistById(accountRequest.getPersonId(), person);
        account.setPerson(person);
        return accountDao
                .getAccountFiltredList(account)
                .stream()
                .map(mapAccount())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AccountView findById(Long id) {
        Account account = accountDao.findById(id);
        checkAccountExistById(id, account);
        return mapAccount().apply(account);
    }

    @Override
    @Transactional
    public void update(AccountView accountView) {
        Account account = accountDao.findById(accountView.getId());
        checkAccountExistById(accountView.getId(), account);
        account.setName(accountView.getName());
        account.setBalance(accountView.getBalance());
        account.setCurrency(accountView.getCurrency());
    }

    @Override
    @Transactional
    public void add(Long personId, AccountSave accountSave) {
        Account account = validate(accountSave);
        Person person = personDao.loadById(personId);
        checkPersonExistById(personId, person);
        person.addAccount(account);
        accountDao.save(account);
    }

    @Override
    public void delete(Long id) {
        Account account = accountDao.findById(id);
        checkAccountExistById(id, account);
        accountDao.delete(account);
    }

    private Account validate(AccountSave accountSave) {
        return null;
    }

    private Function<Account, AccountView> mapAccount() {
        return p -> new AccountView(p.getId(), p.getName(), p.getBalance(), p.getCurrency());
    }


    private  void checkAccountExistById(Long id, Account account) {
        if (account == null) {
            String message = MessageFormat.format("Счета с id {0} нет в базе данных!", id);
            log.error(message);
            throw new AccountNotFoundException(message);
        }
    }
}

