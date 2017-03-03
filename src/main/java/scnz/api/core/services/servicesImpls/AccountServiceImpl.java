package scnz.api.core.services.servicesImpls;

import org.springframework.stereotype.Service;
import scnz.api.core.pojo.Account;
import scnz.api.core.pojo.Item;
import scnz.api.core.services.AccountService;

/**
 * Created by wanghe on 1/03/17.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Account findAccountById(Long accountId) {
        return null;
    }

    @Override
    public Account createAccount(Account accountData) {
        return null;
    }

    @Override
    public Item addItem(Long accountId, Item item) {
        return null;
    }
}
