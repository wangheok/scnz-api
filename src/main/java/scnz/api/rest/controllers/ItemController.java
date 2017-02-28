package scnz.api.rest.controllers;

import com.sun.tracing.dtrace.ProviderAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import scnz.api.core.exceptions.ItemNotFoundException;
import scnz.api.core.pojo.Item;
import scnz.api.core.pojo.ItemEntry;
import scnz.api.core.services.ItemService;
import scnz.api.core.utils.ItemEntryList;
import scnz.api.core.utils.ItemList;
import scnz.api.rest.exceptions.NotFoundException;
import scnz.api.rest.resources.ItemEntryListResource;
import scnz.api.rest.resources.ItemEntryResource;
import scnz.api.rest.resources.ItemListResource;
import scnz.api.rest.resources.ItemResource;
import scnz.api.rest.resources.asm.ItemEntryListResourceAsm;
import scnz.api.rest.resources.asm.ItemEntryResourceAsm;
import scnz.api.rest.resources.asm.ItemListResourceAsm;
import scnz.api.rest.resources.asm.ItemResourceAsm;

import java.net.URI;

/**
 * Created by wanghe on 30/01/17.
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    /**
     * Find all items
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ItemListResource> findAllItems() {
        ItemList itemList = service.findAllItems();
        ItemListResource itemListResource = new ItemListResourceAsm().toResource(itemList);
        return new ResponseEntity<ItemListResource>(itemListResource, HttpStatus.OK);
    }

    /**
     * Retrieve particular item by id
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<ItemResource> getItem(@PathVariable Long itemId) {
        Item item = service.findItemById(itemId);
        if (item != null) {
            ItemResource resource = new ItemResourceAsm().toResource(item);
            return new ResponseEntity<ItemResource>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<ItemResource>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create Item entry
     *
     * @param itemId
     * @param itemEntryResource
     * @return
     */
    @RequestMapping(value = "/{itemId}/item-entries", method = RequestMethod.POST)
    public ResponseEntity<ItemEntryResource> createItemEntry(@PathVariable Long itemId,
                                                             @RequestBody ItemEntryResource itemEntryResource) {
        ItemEntry itemEntry = null;
        try {
            itemEntry = service.createItemEntry(itemId, itemEntryResource.toItemEntry());
            ItemEntryResource createResource = new ItemEntryResourceAsm().toResource(itemEntry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createResource.getLink("self").getHref()));
            return new ResponseEntity<ItemEntryResource>(createResource, headers, HttpStatus.CREATED);
        } catch (ItemNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    /**
     * Find all item entries
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/{itemId}/item-entries", method = RequestMethod.GET)
    public ResponseEntity<ItemEntryListResource> findAllItemEntries(@PathVariable Long itemId) {
        try {
            ItemEntryList itemEntryList = service.findAllItemEntries(itemId);
            ItemEntryListResource itemEntryListResource = new ItemEntryListResourceAsm().toResource(itemEntryList);
            return new ResponseEntity<ItemEntryListResource>(itemEntryListResource, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            throw new NotFoundException(e);
        }
    }
}
