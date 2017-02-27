package scnz.api.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scnz.api.core.pojo.Account;
import scnz.api.rest.controllers.AccountController;
import scnz.api.rest.resources.AccountResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by wanghe on 27/02/17.
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {

    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource accountResource = new AccountResource();
        accountResource.setUsername(account.getUsername());
        accountResource.setPassword(account.getPassword());
        Link link = linkTo(AccountController.class).slash(account.getAccountId()).withSelfRel();
        accountResource.add(link);
        return accountResource;
    }
}
