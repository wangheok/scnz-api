package scnz.api.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import scnz.api.core.pojo.Item;
import scnz.api.core.pojo.ItemEntry;
import scnz.api.core.services.ItemEntryService;
import scnz.api.rest.resources.ItemResource;
import scnz.api.rest.resources.asm.ItemResourcesAsm;

/**
 * Created by wanghe on 30/01/17.
 */
@Controller
@RequestMapping("/items")
public class ItemEntryController {

    private ItemEntryService service;

    public ItemEntryController(ItemEntryService service) {
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
    public ResponseEntity<ItemResource> getItem(@PathVariable Long itemId) {
        ItemEntry item = service.findItemEntry(itemId);
        if (item != null) {
            ItemResource resource = new ItemResourcesAsm().toResource(item);
            return new ResponseEntity<ItemResource>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<ItemResource>(HttpStatus.NOT_FOUND);
        }
    }
}
