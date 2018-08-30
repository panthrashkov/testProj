package ru.rencredit.test.account.service;


import ru.rencredit.test.account.view.AccountRequest;
import ru.rencredit.test.account.view.AccountSave;
import ru.rencredit.test.account.view.AccountView;

import java.util.List;

public interface AccountService {

    /**
     * Получить список счетов клиента
     * @param accountRequest - представление счета
     * @return List<AccountSave> список представлений счета
     */
    List<AccountView> getAccountByOrgId(AccountRequest accountRequest);
    /**
     * Получить счет по id
     * @param id - идентификатор счета
     * @return AccountView - представление счета
     */
    AccountView findById(Long id);
    /**
     * Обновить счет по id
     * @param account представление счета
     */
    void update(AccountView account);
    /**
     * Добавить счет
     * @param personId - идентификатор клиента
     * @param accountSave - представление счета
     */
    void add(Long personId, AccountSave accountSave);
    /**
     * Удалить счет по id
     * @param id - идентификатор счета
     */
    void delete(Long id);
}

