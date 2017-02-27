package scnz.api.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import scnz.api.core.pojo.Item;

/**
 * Created by wanghe on 30/01/17.
 */
public class ItemResource extends ResourceSupport {

    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Item toItem() {
        Item item = new Item();
        item.setItemName(itemName);
        return item;
    }
}
