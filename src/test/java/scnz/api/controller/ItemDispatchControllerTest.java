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
import scnz.api.core.entities.Item;
import scnz.api.rest.controllers.ItemDispatchController;
import scnz.api.core.services.ItemService;

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

    @InjectMocks
    private ItemDispatchController controller;

    @Mock
    private ItemService service;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getResource() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/items")
                .content("{\"itemId\": \"Mac\", \"itemName\": \"Laptop\"}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());
//        MockMvcResultMatchers
    }


    @Test
    public void getCurrentStock() throws Exception {
        Item item = new Item("001", "ThinkPad");
        when(service.retrieve("001")).thenReturn(item);

        mockMvc.perform(get("/items/001"))
                .andDo(print())
                .andExpect(jsonPath("$.itemName", is(item.getItemName())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/items/001"))))
//                .andExpect(jsonPath("$.links[0].href", CoreMatchers.endsWith("/items/001")))
                .andExpect(status().isOk());
    }
}