package ru.rencredit.test.account.dao;

import ru.rencredit.test.account.model.Account;

import java.util.List;

public interface AccountDao {

    /*
    получить счет по ID клиента
     */
    List<Account> getAccountFiltredList(Account account);

    /*
    получить счет по ID
     */
    Account findById(Long id);

    /*
    добавить счет в список
     */
    void save(Account account);

    void update(Account account);

    /*
    удалить счет по ID
     */
    void delete(Long id);


}
