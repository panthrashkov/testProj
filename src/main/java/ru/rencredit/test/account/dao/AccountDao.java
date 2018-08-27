package ru.rencredit.test.account.dao;

import ru.rencredit.test.account.model.Account;
import ru.rencredit.test.account.view.AccountView;

import java.util.List;

public interface AccountDao {

    /*
    получить счет по ID клиента
     */
    List<Account> getAccountByOrgId(AccountView accountView);

    /*
    получить счет по ID
     */
    Account findById(Long id);

    /*
    добавить счет в список
     */
    void save(Account account);

    /*
    получить список всех счетов
     */
    List<Account> getAllAccount();

    /*
    удалить счет по ID
     */
    void delete(Long id);

}
