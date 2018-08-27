package ru.rencredit.test.account.service;


import ru.rencredit.test.account.view.AccountView;

import java.util.List;

public interface AccountService {

    /*
    получить офис по ID организации (с помощью @RequestBody)
    */
    List<AccountView> getAccountByOrgId(AccountViewRequest accountViewRequest) ;

    /*
    получить офис по ID
    */
    AccountViewLoadById findById(Long id);

    /*
    обновить данные офиса
    */
    void update(AccountViewLoadById account);

    /*
    добавить офис
    */
    void add(AccountViewSave account);

    /*
    получить весь список офисов
    */
    List<AccountViewLoadById> getAllAccount();

    /*
    удалить офис по ID
    */
    void delete(Long id);
}

