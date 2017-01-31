package scnz.api.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scnz.api.core.entities.Item;
import scnz.api.rest.controllers.ItemDispatchController;
import scnz.api.rest.resources.ItemResources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by wanghe on 30/01/17.
 */
public class ItemResourcesAsm extends ResourceAssemblerSupport<Item, ItemResources> {

    public ItemResourcesAsm() {
        super(ItemDispatchController.class, ItemResources.class);
    }

    @Override
    public ItemResources toResource(Item item) {
        ItemResources resources = new ItemResources();
        resources.setItemName(item.getItemName());
        Link link = linkTo(ItemDispatchController.class).slash(item.getItemId()).withSelfRel();
        resources.add(link);
        System.out.println("~~~~~~~~~~~~~~" + resources);
        return resources;
    }
}
