package scnz.api.controller;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import scnz.api.core.pojo.Item;
import scnz.api.rest.controllers.ItemController;
import scnz.api.core.services.ItemEntryService;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wanghe on 30/01/17.
 */
public class ItemDispatchControllerTest {
    @Mock
    private ItemEntryService service;

    @InjectMocks
    private ItemController controller;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        System.out.println("Before setup");
    }

    /**
     * Post: /items
     *
     * @throws Exception
     */
    @Test
    public void getResource() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/items")
                .content("{\"itemId\": \"2\", \"itemName\": \"MacBook\"}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.itemId", is(2))).andDo(print());
    }

    /**
     * Get: /items/{itemId}
     *
     * @throws Exception
     */
    @Test
    public void getSpecificItem() throws Exception {
        Item item = new Item();
        item.setItemId(1L);
        item.setItemName("ThinkPad");
        when(service.retrieve(1L)).thenReturn(item);

        mockMvc.perform(get("/items/1"))
                .andDo(print())
                .andExpect(jsonPath("$.itemName", is(item.getItemName())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/items/1"))))
                .andExpect(status().isOk());
    }

}