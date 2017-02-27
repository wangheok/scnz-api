package scnz.api.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import scnz.api.core.pojo.ItemEntry;

/**
 * Created by wanghe on 27/02/17.
 */
public class ItemEntryResource extends ResourceSupport {

    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemEntry toItemEntry() {
        ItemEntry itemEntry = new ItemEntry();
        itemEntry.setItemName(itemName);
        return itemEntry;
    }
}
