package scnz.api.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghe on 27/02/17.
 */
public class ItemListResource extends ResourceSupport {

    private List<ItemResource> itemResourceList = new ArrayList<>();

    public List<ItemResource> getItemResourceList() {
        return itemResourceList;
    }

    public void setItemResourceList(List<ItemResource> itemResourceList) {
        this.itemResourceList = itemResourceList;
    }
}
