package scnz.api.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scnz.api.core.utils.ItemEntryList;
import scnz.api.rest.controllers.ItemController;
import scnz.api.rest.resources.ItemEntryListResource;
import scnz.api.rest.resources.ItemEntryResource;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by wanghe on 28/02/17.
 */
public class ItemEntryListResourceAsm extends ResourceAssemblerSupport<ItemEntryList, ItemEntryListResource> {
    public ItemEntryListResourceAsm() {
        super(ItemController.class, ItemEntryListResource.class);
    }

    @Override
    public ItemEntryListResource toResource(ItemEntryList itemEntryList) {
        List<ItemEntryResource> itemEntryResourceList = new ItemEntryResourceAsm().toResources(itemEntryList.getItemEntries());
        ItemEntryListResource itemEntryListResource = new ItemEntryListResource();
        itemEntryListResource.setItemEntryResourceList(itemEntryResourceList);
        itemEntryListResource.add(linkTo(methodOn(ItemController.class).findAllItemEntries(itemEntryList.getItemId())).withSelfRel());
        return itemEntryListResource;
    }
}
