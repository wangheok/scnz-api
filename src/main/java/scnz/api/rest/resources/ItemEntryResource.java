package scnz.api.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import scnz.api.core.pojo.ItemEntry;

/**
 * Created by wanghe on 27/02/17.
 */
public class ItemEntryResource extends ResourceSupport {

    private String itemEntryName;

    public String getItemEntryName() {
        return itemEntryName;
    }

    public void setItemEntryName(String itemEntryName) {
        this.itemEntryName = itemEntryName;
    }

    public ItemEntry toItemEntry() {
        ItemEntry itemEntry = new ItemEntry();
        itemEntry.setItemEntryName(itemEntryName);
        return itemEntry;
    }
}
