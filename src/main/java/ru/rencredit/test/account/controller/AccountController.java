package ru.rencredit.test.account.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.rencredit.test.account.service.AccountService;
import ru.rencredit.test.account.view.AccountView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /*
    получить офис по ID организации (с помощью @RequestBody)
    */
    @ApiOperation(value = "/api/account/list", nickname = "получить офис по ID организации", httpMethod = "POST")
    @PostMapping("/api/account/list/{orgId}")
    public List<AccountView> getAccountListByOrgId(@RequestBody AccountViewRequest accountViewRequest ) {
        return accountService.getAccountByOrgId(accountViewRequest); }

    /*
    получить офис по ID
    */
    @ApiOperation(value = "api/account/{id}", nickname = "получить офис по ID", httpMethod = "GET")
    @GetMapping("/api/account/{id}")
    public AccountViewLoadById loadById (@PathVariable Long id) {
        return accountService.findById(id);
    }

    /*
    обновить данные офисa
    */
    @ApiOperation(value = "update", nickname = "update", httpMethod = "POST")
    @PostMapping("/api/account/update")
    public void update(@RequestBody AccountViewLoadById account) {
        accountService.update(account);
    }

    /*
    добавить офис
    */
    @ApiOperation(value = "api/account/save", nickname = "api/account/save",
            httpMethod = "POST")
    @PostMapping("/api/account/save")
    public void add( @RequestBody AccountViewSave accountViewSave) {
        accountService.add(accountViewSave);
    }

    /*
    получить весь список офисов
    */
    @ApiOperation(value = "getAllAccount", nickname = "getAllAccount", httpMethod = "GET")
    @GetMapping("/api/account/all")
    public List<AccountViewLoadById> getAllAccount() {
        return accountService.getAllAccount();
    }

    /*
    удалить офис по ID
    */
    @ApiOperation(value = "deleteAccount", nickname = "deleteAccount", httpMethod = "POST")
    @PostMapping("/api/account/delete/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}