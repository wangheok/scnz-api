package scnz.api.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import scnz.api.core.utils.ItemList;
import scnz.api.rest.controllers.ItemController;
import scnz.api.rest.resources.ItemListResource;
import scnz.api.rest.resources.ItemResource;

/**
 * Created by wanghe on 28/02/17.
 */
public class ItemListResourceAsm extends ResourceAssemblerSupport<ItemList, ItemListResource> {

    public ItemListResourceAsm() {
        super(ItemController.class, ItemListResource.class);
    }

    @Override
    public ItemListResource toResource(ItemList itemList) {
        ItemListResource itemListResource = new ItemListResource();
        itemListResource.setItemResourceList(new ItemResourceAsm().toResources(itemList.getItems()));
        return itemListResource;
    }
}
