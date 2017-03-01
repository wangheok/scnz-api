package scnz.api.rest.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import scnz.api.core.exceptions.ItemNotFoundException;
import scnz.api.core.pojo.Account;
import scnz.api.core.pojo.Item;
import scnz.api.core.pojo.ItemEntry;
import scnz.api.core.services.ItemService;
import scnz.api.core.utils.ItemEntryList;
import scnz.api.core.utils.ItemList;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wanghe on 28/02/17.
 */
public class ItemControllerTest {

    @Mock
    ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    /**
     * Find all items
     *
     * @throws Exception
     */
    @Test
    public void findAllItems() throws Exception {
        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item();
        item1.setItemId(1L);
        item1.setItemName("Item #1");
        itemList.add(item1);
        Item item2 = new Item();
        item2.setItemId(2L);
        item2.setItemName("Item #2");
        itemList.add(item2);

        ItemList allItems = new ItemList();
        allItems.setItems(itemList);

        when(itemService.findAllItems()).thenReturn(allItems);

        mockMvc.perform(get("/items"))
                .andDo(print())
                .andExpect(jsonPath("$.itemResourceList[*].itemName",
                        hasItems(endsWith("Item #1"), endsWith("Item #2"))))
                .andExpect(status().isOk());
    }

    /**
     * Find item by specifying Id
     *
     * @throws Exception
     */
    @Test
    public void getItem() throws Exception {
        Item item = new Item();
        item.setItemId(1L);
        item.setItemName("Test getItem()");

        Account account = new Account();
        account.setAccountId(99L);
        item.setAccount(account);

        when(itemService.findItemById(1L)).thenReturn(item);

        mockMvc.perform(get("/items/1"))
                .andDo(print())
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/items/1"))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/items/1/entries"))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/items/99"))))
                .andExpect(jsonPath("$.links[*].rel", hasItems(is("self"), is("entries"), is("account"))))
                .andExpect(jsonPath("$.itemName", is("Test getItem()")))
                .andExpect(status().isOk());

    }

    /**
     * Creating item entry for existing item
     *
     * @throws Exception
     */
    @Test
    public void createItemEntryForExistingItem() throws Exception {
        Item item = new Item();
        item.setItemId(1L);
        item.setItemName("Existing Item Name");

        ItemEntry itemEntry = new ItemEntry();
        itemEntry.setItemEntryId(1L);
        itemEntry.setItemEntryName("Create item entry");

        when(itemService.createItemEntry(eq(1L), any(ItemEntry.class))).thenReturn(itemEntry);

        mockMvc.perform(post("/items/1/item-entries")
                .content("{\"itemName\": \"Generic item Name\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.itemEntryName", is(itemEntry.getItemEntryName())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/item-entries/1"))))
                .andExpect(header().string("Location", endsWith("/item-entries/1")))
                .andExpect(status().isCreated());
    }

    /**
     * Creating item entry for existing item
     *
     * @throws Exception
     */
    @Test
    public void createItemEntryForNonExistingItem() throws Exception {
        when(itemService.createItemEntry(eq(1L), any(ItemEntry.class))).thenThrow(new ItemNotFoundException());

        mockMvc.perform(post("/items/1/item-entries")
                .content("{\"itemName\": \"Generic item Name\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Find all item entries for existing item
     *
     * @throws Exception
     */
    @Test
    public void findAllItemEntriesForExistingItem() throws Exception {
        ItemEntry itemEntry1 = new ItemEntry();
        itemEntry1.setItemEntryId(1L);
        itemEntry1.setItemEntryName("Item Entry #1");

        ItemEntry itemEntry2 = new ItemEntry();
        itemEntry2.setItemEntryId(2L);
        itemEntry2.setItemEntryName("Item Entry #2");

        List<ItemEntry> itemEntryList = new ArrayList<>();
        itemEntryList.add(itemEntry1);
        itemEntryList.add(itemEntry2);

        ItemEntryList allItemEntries = new ItemEntryList();
        allItemEntries.setItemEntries(itemEntryList);
        allItemEntries.setItemId(1L);

        when(itemService.findAllItemEntries(1L)).thenReturn(allItemEntries);

        mockMvc.perform(get("/items/1/item-entries"))
                .andDo(print())
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/items/1/item-entries"))))
                .andExpect(jsonPath("$.itemEntryResourceList[*].itemEntryName", hasItem(is("Item Entry #2"))))
                .andExpect(status().isOk());

    }

    /**
     * Find all item entries for non-existing item
     *
     * @throws Exception
     */
    @Test
    public void findAllItemEntriesForNonExistingItem() throws Exception {
        when(itemService.findAllItemEntries(2L)).thenThrow(new ItemNotFoundException());

        mockMvc.perform(get("/items/2/item-entries"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}