package scnz.api.rest.controllers;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scnz.api.core.exceptions.AccountDoesNotExistException;
import scnz.api.core.exceptions.AccountExistsException;
import scnz.api.core.exceptions.ItemExistsException;
import scnz.api.core.pojo.Account;
import scnz.api.core.pojo.Item;
import scnz.api.core.services.AccountService;
import scnz.api.rest.exceptions.BadRequestException;
import scnz.api.rest.exceptions.ConflictException;
import scnz.api.rest.resources.AccountResource;
import scnz.api.rest.resources.ItemResource;
import scnz.api.rest.resources.asm.AccountResourceAsm;
import scnz.api.rest.resources.asm.ItemResourceAsm;

import java.net.URI;

/**
 * Created by wanghe on 27/02/17.
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Create new account
     *
     * @param accountResouce
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource accountResouce) {
        try {
            Account account = accountService.createAccount(accountResouce.toAccount());
            AccountResource accountResource = new AccountResourceAsm().toResource(account);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(accountResource.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(accountResource, headers, HttpStatus.CREATED);
        } catch (AccountExistsException e) {
            throw new ConflictException(e);
        }
    }

    /**
     * Find the account by specifying account ID
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId) {
        Account account = accountService.findAccountById(accountId);
        if (account == null) {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
        AccountResource accountResource = new AccountResourceAsm().toResource(account);
        return new ResponseEntity<AccountResource>(accountResource, HttpStatus.OK);
    }

    /**
     * Add an item
     *
     * @param accountId
     * @param itemResource
     * @return
     */
    @RequestMapping(value = "/{accountId}/items", method = RequestMethod.POST)
    public ResponseEntity<ItemResource> addItem(@PathVariable Long accountId,
                                                @RequestBody ItemResource itemResource) {
        try {
            Item addedItem = accountService.addItem(accountId, itemResource.toItem());
            ItemResource addedItemResource = new ItemResourceAsm().toResource(addedItem);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(addedItemResource.getLink("self").getHref()));
            return new ResponseEntity<ItemResource>(addedItemResource, headers, HttpStatus.CREATED);
        } catch (AccountDoesNotExistException e) {
            throw new BadRequestException(e);
        } catch (ItemExistsException e) {
            throw new ConflictException(e);
        }
    }
}
