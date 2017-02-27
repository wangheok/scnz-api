package scnz.api.rest.controllers;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import scnz.api.core.exceptions.AccountDoesNotExistException;
import scnz.api.core.exceptions.AccountExistsException;
import scnz.api.core.exceptions.ItemExistsException;
import scnz.api.core.pojo.Account;
import scnz.api.core.pojo.Item;
import scnz.api.core.services.AccountService;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wanghe on 27/02/17.
 */
public class AccountControllerTest {
    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    /**
     * Test create a new account
     *
     * @throws Exception
     */
    @Test
    public void createNonExistingAccount() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setAccountId(1L);
        createdAccount.setUsername("admin");
        createdAccount.setPassword("password");

        when(accountService.createAccount(any(Account.class))).thenReturn(createdAccount);

        mockMvc.perform(post("/accounts")
                .content("{\"username\": \"admin\", \"password\": \"password\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(header().string("Location", endsWith("/accounts/1")))
                .andExpect(jsonPath("$.username", is(createdAccount.getUsername())))
                .andExpect(status().isCreated());
    }

    /**
     * Test create an account who has been existing
     *
     * @throws Exception
     */
    @Test
    public void createExistingAccount() throws Exception {
        Account createAccount = new Account();
        createAccount.setAccountId(2L);
        createAccount.setUsername("admin");
        createAccount.setPassword("password");
        when(accountService.createAccount(any(Account.class))).thenThrow(new AccountExistsException());

        mockMvc.perform(post("/accounts")
                .content("{\"username\": \"admin\", \"password\": \"password\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    /**
     * Get existing account
     *
     * @throws Exception
     */
    @Test
    public void getExistingAccount() throws Exception {

        Account account = new Account();
        account.setAccountId(3L);
        account.setUsername("admin");
        account.setPassword("password");
        when(accountService.findAccountById(3L)).thenReturn(account);

        mockMvc.perform(get("/accounts/3"))
                .andDo(print())
                .andExpect(jsonPath("$.username", is(account.getUsername())))
                .andExpect(jsonPath("$.password", is(nullValue())))
                .andExpect(status().isOk());
    }

    /**
     * Test find non-existing account
     *
     * @throws Exception
     */
    @Test
    public void getNonExistingAccount() throws Exception {
        when(accountService.findAccountById(3L)).thenReturn(null);
        mockMvc.perform(get("/accounts/3"))
                .andExpect(status().isNotFound());
    }

    /**
     * Add item to existing account
     *
     * @throws Exception
     */
    @Test
    public void addItemToExistingAccount() throws Exception {
        Item item = new Item();
        item.setItemId(1L);
        item.setItemName("addItemToExistingAccount");
        when(accountService.addItem(eq(1L), any(Item.class))).thenReturn(item);

        mockMvc.perform(post("/accounts/1/items")
                .content("{\"itemName\": \"addItemToExistingAccount\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.itemName", is("addItemToExistingAccount")))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/items/1"))))
                .andExpect(header().string("Location", endsWith("/items/1")))
                .andExpect(status().isCreated());
    }

    /**
     * Add item to Non-Existing Account
     *
     * @throws Exception
     */
    @Test
    public void addItemToNonExistingAccount() throws Exception {
        when(accountService.addItem(eq(1L), any(Item.class))).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(post("/accounts/1/items")
                .content("{\"itemName\": \"addItemToExistingAccount\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addDuplicateItem() throws Exception {
        when(accountService.addItem(eq(1L), any(Item.class))).thenThrow(new ItemExistsException());

        mockMvc.perform(post("/accounts/1/items")
                .content("{\"itemName\": \"addItemToExistingAccount\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}