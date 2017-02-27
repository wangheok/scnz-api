package scnz.api.core.utils;

import scnz.api.core.pojo.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghe on 27/02/17.
 */
public class ItemList {

    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
