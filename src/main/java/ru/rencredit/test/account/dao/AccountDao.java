package ru.rencredit.test.account.dao;

import ru.rencredit.test.account.model.Account;

import java.util.List;

public interface AccountDao {

    /**
     * Получить список счетов клиента
     * @param account - модель счета
     * @return List<Account> список счетов
     */
    List<Account> getAccountFiltredList(Account account);

    /**
     * Получить счет по id
     * @param id - идентификатор счета
     * @return Account - модель счета
     */
    Account findById(Long id);

    /**
     * Добавить счет
     * @param account - идентификатор клиента
     */
    void save(Account account);

    /**
     * Удалить счет
     * @param account - идентификатор счета
     */
    void delete(Account account);


}
