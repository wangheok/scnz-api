package scnz.api.rest.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by wanghe on 30/01/17.
 */
public class ItemResources extends ResourceSupport {

    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
