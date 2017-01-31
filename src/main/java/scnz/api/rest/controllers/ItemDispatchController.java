package scnz.api.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import scnz.api.core.entities.Item;
import scnz.api.rest.resources.ItemResources;
import scnz.api.rest.resources.asm.ItemResourcesAsm;
import scnz.api.core.services.ItemService;

/**
 * Created by wanghe on 30/01/17.
 */
@Controller
@RequestMapping("/items")
public class ItemDispatchController {

    private ItemService service;

    public ItemDispatchController(ItemService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    Item test(@RequestBody Item item) {
        return item;
    }

    /**
     * Retrieve particular item
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<ItemResources> getItem(@PathVariable String itemId) {
        Item item = service.retrieve(itemId);
        if (item != null) {
            ItemResources resources = new ItemResourcesAsm().toResource(item);
            return new ResponseEntity<ItemResources>(resources, HttpStatus.OK);
        } else {
            return new ResponseEntity<ItemResources>(HttpStatus.NOT_FOUND);
        }
    }
}
