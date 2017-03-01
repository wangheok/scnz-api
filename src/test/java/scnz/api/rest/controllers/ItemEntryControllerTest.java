package scnz.api.rest.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import scnz.api.core.pojo.Item;
import scnz.api.core.pojo.ItemEntry;
import scnz.api.core.services.ItemEntryService;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wanghe on 1/03/17.
 */
public class ItemEntryControllerTest {

    @Mock
    ItemEntryService itemEntryService;

    @InjectMocks
    ItemEntryController itemEntryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemEntryController).build();
    }

    /**
     * Get an existing item entry
     *
     * @throws Exception
     */
    @Test
    public void getExistingItemEntry() throws Exception {
        ItemEntry itemEntry = new ItemEntry();
        itemEntry.setItemEntryId(1L);
        itemEntry.setItemEntryName("ItemEntry Name");

        Item item = new Item();
        item.setItemId(3L);

        itemEntry.setItem(item);

        when(itemEntryService.findItemEntry(1L)).thenReturn(itemEntry);

        mockMvc.perform(get("/item-entries/1"))
                .andDo(print())
                .andExpect(jsonPath("$.itemEntryName", is(itemEntry.getItemEntryName())))
                .andExpect(jsonPath("$.links[*].href",
                        hasItems(endsWith("/items/3"), endsWith("/item-entries/1"))))
                .andExpect(jsonPath("$.links[*].rel",
                        hasItems(is("self"), is("item"))))
                .andExpect(status().isOk());
    }

    /**
     * Get item entry which not existing
     *
     * @throws Exception
     */
    @Test
    public void getNonExistingItemEntry() throws Exception {
        when(itemEntryService.findItemEntry(2L)).thenReturn(null);

        mockMvc.perform(get("/item-entries/2")).andExpect(status().isNotFound());
    }

    /**
     * Delete existing item entry
     *
     * @throws Exception
     */
    @Test
    public void deleteExistingItemEntry() throws Exception {
        ItemEntry delItemEntry = new ItemEntry();
        delItemEntry.setItemEntryId(4L);
        delItemEntry.setItemEntryName("Delete item entry");

        when(itemEntryService.deleteItemEntry(4L)).thenReturn(delItemEntry);

        mockMvc.perform(delete("/item-entries/4"))
                .andExpect(jsonPath("$.itemEntryName", is(delItemEntry.getItemEntryName())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/item-entries/4"))))
                .andExpect(status().isOk());
    }

    /**
     * Delete non-existing item entry
     *
     * @throws Exception
     */
    @Test
    public void deleteNonExistingItemEntry() throws Exception {
        when(itemEntryService.deleteItemEntry(5L)).thenReturn(null);

        mockMvc.perform(delete("/item-entries/5")).andExpect(status().isNotFound());
    }

    /**
     * Update existing item entry
     *
     * @throws Exception
     */
    @Test
    public void updateExistingItemEntry() throws Exception {
        ItemEntry itemEntry = new ItemEntry();
        itemEntry.setItemEntryId(88L);
        itemEntry.setItemEntryName("Update entry");

        when(itemEntryService.updateItemEntry(eq(88L), any(ItemEntry.class))).thenReturn(itemEntry);

        mockMvc.perform(put("/item-entries/88")
                .content("{\"itemEntryName\": \"UpdateEntry\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.itemEntryName", is(itemEntry.getItemEntryName())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/item-entries/88"))))
                .andExpect(status().isOk());

    }

    /**
     * Update non-existing item entry
     *
     * @throws Exception
     */
    @Test
    public void updateNonExistingItemEntry() throws Exception {
        when(itemEntryService.updateItemEntry(eq(99L), any(ItemEntry.class))).thenReturn(null);

        mockMvc.perform(put("/item-entries/99")
                .content("{\"itemEntryName\": \"UpdateEntry\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}