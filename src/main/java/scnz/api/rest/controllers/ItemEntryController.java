package scnz.api.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import scnz.api.core.pojo.ItemEntry;
import scnz.api.core.services.ItemEntryService;
import scnz.api.rest.resources.ItemEntryResource;
import scnz.api.rest.resources.asm.ItemEntryResourceAsm;

/**
 * Created by wanghe on 30/01/17.
 */
@Controller
@RequestMapping("/item-entries")
public class ItemEntryController {

    private ItemEntryService service;

    public ItemEntryController(ItemEntryService service) {
        this.service = service;
    }

    /**
     * Get specific item entry by ID
     *
     * @param itemEntryId
     * @return
     */
    @RequestMapping(value = "/{itemEntryId}", method = RequestMethod.GET)
    public ResponseEntity<ItemEntryResource> getItemEntry(@PathVariable Long itemEntryId) {
        ItemEntry itemEntry = service.findItemEntry(itemEntryId);
        if (itemEntry == null) {
            return new ResponseEntity<ItemEntryResource>(HttpStatus.NOT_FOUND);
        }
        ItemEntryResource itemEntryResource = new ItemEntryResourceAsm().toResource(itemEntry);
        return new ResponseEntity<ItemEntryResource>(itemEntryResource, HttpStatus.OK);
    }

    /**
     * Delete item entry by Id
     *
     * @param itemEntryId
     * @return
     */
    @RequestMapping(value = "/{itemEntryId}", method = RequestMethod.DELETE)
    public ResponseEntity<ItemEntryResource> deleteItemEntry(@PathVariable Long itemEntryId) {
        ItemEntry itemEntry = service.deleteItemEntry(itemEntryId);
        if (itemEntry == null) {
            return new ResponseEntity<ItemEntryResource>(HttpStatus.NOT_FOUND);
        }
        ItemEntryResource itemEntryResource = new ItemEntryResourceAsm().toResource(itemEntry);
        return new ResponseEntity<ItemEntryResource>(itemEntryResource, HttpStatus.OK);
    }

    /**
     * Update item entry by ID
     *
     * @param itemEntryId
     * @param itemEntryResource
     * @return
     */
    @RequestMapping(value = "/{itemEntryId}", method = RequestMethod.PUT)
    public ResponseEntity<ItemEntryResource> updateItemEntry(@PathVariable Long itemEntryId,
                                                             @RequestBody ItemEntryResource itemEntryResource) {
        ItemEntry updateEntry = service.updateItemEntry(itemEntryId, itemEntryResource.toItemEntry());
        if (updateEntry == null) {
            return new ResponseEntity<ItemEntryResource>(HttpStatus.NOT_FOUND);
        }
        ItemEntryResource resource = new ItemEntryResourceAsm().toResource(updateEntry);
        return new ResponseEntity<ItemEntryResource>(resource, HttpStatus.OK);
    }

}
