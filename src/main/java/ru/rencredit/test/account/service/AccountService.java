package ru.rencredit.test.account.service;


import ru.rencredit.test.account.view.AccountView;

import java.util.List;

public interface AccountService {

    /**
     * Получить список счетов клиента
     * @param accountView - представление счета
     * @return List<AccountView> список представлений счета
     */
    List<AccountView> getAccountByOrgId(AccountView accountView);
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
     * @param accountView - представление счета
     */
    void add(Long personId, AccountView accountView);
    /**
     * Удалить счет по id
     * @param id
     */
    void delete(Long id);
}

