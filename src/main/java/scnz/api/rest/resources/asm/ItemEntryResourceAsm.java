package scnz.api.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scnz.api.core.pojo.Item;
import scnz.api.core.pojo.ItemEntry;
import scnz.api.rest.controllers.ItemController;
import scnz.api.rest.controllers.ItemEntryController;
import scnz.api.rest.resources.ItemEntryResource;
import scnz.api.rest.resources.ItemResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by wanghe on 30/01/17.
 */
public class ItemEntryResourceAsm extends ResourceAssemblerSupport<ItemEntry, ItemEntryResource> {

    public ItemEntryResourceAsm() {
        super(ItemEntryController.class, ItemEntryResource.class);
    }

    @Override
    public ItemEntryResource toResource(ItemEntry itemEntry) {
        ItemEntryResource itemEntryResource = new ItemEntryResource();
        itemEntryResource.setItemEntryName(itemEntry.getItemEntryName());
        itemEntryResource.add(linkTo(ItemEntryController.class).slash(itemEntry.getItemEntryId()).withSelfRel());
        if (itemEntry.getItem() != null) {
            itemEntryResource.add(linkTo(ItemController.class).slash(itemEntry.getItem().getItemId()).withRel("item"));
        }
        return itemEntryResource;
    }
}
