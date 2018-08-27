package ru.rencredit.test.account.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rencredit.test.account.dao.AccountDao;
import ru.rencredit.test.account.model.Account;
import ru.rencredit.test.account.view.AccountView;
import ru.rencredit.test.person.dao.PersonDao;
import ru.rencredit.test.person.model.Person;

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

    /*
    получить офис по ID организации
    */
    @Override
    public List<AccountView> getAccountByOrgId(AccountViewRequest accountViewRequest) {
        if (accountViewRequest.orgId == null) {
            throw new OrganisationValidationException("Поле orgId является обязательным");
        }
        if (accountViewRequest.phoneAccount != null) {
            validatePhone(accountViewRequest.phoneAccount);
        }
        List<Account> accountList;
        accountList = accountDao.getAccountByOrgId(accountViewRequest);

        return accountList.stream().map(elem -> new AccountView(elem.getId(), elem.getName(), elem.getIsActive())).collect(Collectors.toList());
    }

    /*
    получить офис по ID
    */
    @Override
    public AccountViewLoadById findById(Long id) {
        AccountViewLoadById accountViewLoadById = new AccountViewLoadById();
        try{
            Account account = accountDao.findById(id);
            accountViewLoadById.id = account.getId();
            accountViewLoadById.name = account.getName();
            accountViewLoadById.address = account.getAddress();
            accountViewLoadById.phoneAccount = account.getPhoneAccount();
            accountViewLoadById.isActive = account.getIsActive();
            System.out.println(account.getUsers());
        }catch (EmptyResultDataAccessException e){
            throw new OrgOutException("Офиса с таким ID нет в базе данных");
        }

        return accountViewLoadById;
    }

    /*
    обновить данные офисa
    */
    @Override
    public void update(AccountViewLoadById account) {
        Account account1;
        try {
             account1 = accountDao.findById(account.id);
        }catch (EmptyResultDataAccessException e) {
            throw new OrgOutException("Офиса с таким ID нет в базе данных");
        }
        if ((account.name == null) || (account.address == null) || (account.isActive == null)) {
            throw new OrgOutException("Заполнены не все обязательные поля");
        }
        account1.setName(account.name);
        account1.setAddress(account.address);
        account1.setIsActive(account.isActive);
        if (account.phoneAccount == null) {
            account.phoneAccount = account1.getPhoneAccount();
            account1.setPhoneAccount(account.phoneAccount);
        } else {
            validatePhone(account.phoneAccount);
            account1.setPhoneAccount(account.phoneAccount);
        }
    }

    /*
    добавить новый офис
    */
    @Override
    public void add(AccountViewSave account) {
        if (account.phoneAccount != null) {
            validatePhone(account.phoneAccount);
        }
        Account account1 = new Account (account.name, account.address, account.phoneAccount, account.isActive);
        if (account.orgId != null) {
            try {
                Person person = personDao.loadById(account.orgId);
                person.addAccount(account1);
            } catch (EmptyResultDataAccessException e) {
                throw new OrganisationValidationException("Невозможно привязать офис у казанному orgId, т.к. организации" +
                        " с таким ID нет в БД");
            }
        }
        accountDao.save(account1);
    }

    /*
    получить список всех офисов
    */
    @Override
    public List<AccountViewLoadById> getAllAccount() {
        List<Account> all = accountDao.getAllAccount();
        return all.stream()
                .map(mapAccount())
                .collect(Collectors.toList());
    }
    private Function<Account, AccountViewLoadById> mapAccount() {
        return p -> {

            AccountViewLoadById view = new AccountViewLoadById();
            view.id = Long.valueOf(p.getId());
            view.name = p.getName();
            view.address = p.getAddress();
            view.isActive = p.getIsActive();
            view.phoneAccount = p.getPhoneAccount();
            return view;
        };
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Account account;
        try {
            account = accountDao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new OrganisationValidationException("Офиса с таким ID нет в БД");
        }
        if (account.getPerson() != null) {
            account.getPerson().removeAccount(account);
        } else {
            accountDao.delete(id);
        }
    }

    private Long validatePhone(String a) {
        Long aLong;
        try {
            aLong = Long.parseLong(a);
        } catch (NumberFormatException e) {
            throw new OrganisationValidationException("Телефон должен состоять из цифр");
        }
        return aLong;
    }
}

