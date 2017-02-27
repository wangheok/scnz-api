package scnz.api.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import scnz.api.core.pojo.ItemEntry;

import java.util.List;

/**
 * Created by wanghe on 27/02/17.
 */
public class ItemEntryListResource extends ResourceSupport {

    private String itemName;
    private List<ItemEntryResource> itemEntryResourceList;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<ItemEntryResource> getItemEntryResourceList() {
        return itemEntryResourceList;
    }

    public void setItemEntryResourceList(List<ItemEntryResource> itemEntryResourceList) {
        this.itemEntryResourceList = itemEntryResourceList;
    }
}
