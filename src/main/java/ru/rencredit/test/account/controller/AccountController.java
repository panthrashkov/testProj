package ru.rencredit.test.account.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.rencredit.test.account.service.AccountService;
import ru.rencredit.test.account.view.AccountRequest;
import ru.rencredit.test.account.view.AccountSave;
import ru.rencredit.test.account.view.AccountView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/account", produces = APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Получить список счетов клиента
     * @param accountRequest - представление счета
     * @return List<AccountSave> список представлений счета
     */
    @ApiOperation(value = "/api/account/list", nickname = "/api/person", httpMethod = "POST")
    @PostMapping("/api/account/list")
    public List<AccountView> getAccountListByPersonId(@RequestBody AccountRequest accountRequest) {
        return accountService.getAccountByOrgId(accountRequest);
    }

    /**
     * Получить счет по id
     * @param id - идентификатор счета
     * @return AccountSave - представление счета
     */
    @ApiOperation(value = "/api/account/{id}", nickname = "получить счет по ID", httpMethod = "GET")
    @GetMapping("/{id}")
    public AccountView loadById (@PathVariable Long id) {
        return accountService.findById(id);
    }

    /**
     * Обновить счет по id
     * @param account представление счета
     */
    @ApiOperation(value = "/api/account/update", nickname = "update", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody AccountView account) {
        accountService.update(account);
    }

    /**
     * Добавить счет
     * @param personId - идентификатор клиента
     * @param accountView - представление счета
     */
    @ApiOperation(value = "api/account/save", nickname = "api/account/save",
            httpMethod = "POST")
    @PostMapping("/save")
    public void add( @PathVariable Long personId, @RequestBody AccountSave accountView) {
        accountService.add(personId, accountView);
    }

    /**
     * Удалить счет по id
     * @param id
     */
    @ApiOperation(value = "/api/account/delete/{id}", nickname = "deleteAccount", httpMethod = "POST")
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}