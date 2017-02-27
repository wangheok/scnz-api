package scnz.api.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scnz.api.core.pojo.Item;
import scnz.api.rest.controllers.ItemController;
import scnz.api.rest.resources.ItemResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by wanghe on 30/01/17.
 */
public class ItemResourceAsm extends ResourceAssemblerSupport<Item, ItemResource> {

    public ItemResourceAsm() {
        super(ItemController.class, ItemResource.class);
    }

    @Override
    public ItemResource toResource(Item item) {
        ItemResource resources = new ItemResource();
        resources.setItemName(item.getItemName());
        resources.add(linkTo(ItemController.class).slash(item.getItemId()).withSelfRel());
        resources.add(linkTo(ItemController.class).slash(item.getItemId()).slash("entries").withRel("entries"));
        if (item.getAccount() != null) {
            resources.add(linkTo(ItemController.class).slash(item.getAccount().getAccountId()).withRel("account"));
        }
        return resources;
    }
}
