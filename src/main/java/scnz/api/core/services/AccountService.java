package scnz.api.core.services;

import scnz.api.core.pojo.Account;
import scnz.api.core.pojo.Item;

/**
 * Created by wanghe on 27/02/17.
 */
public interface AccountService {

    /**
     * Find the account information by account id
     *
     * @param accountId
     * @return
     */
    public Account findAccountById(Long accountId);

    /**
     * Create a new account
     *
     * @param accountData
     * @return
     */
    public Account createAccount(Account accountData);

    /**
     * Add new item to the account
     *
     * @param accountId
     * @param item
     * @return
     */
    public Item addItem(Long accountId, Item item);

}
