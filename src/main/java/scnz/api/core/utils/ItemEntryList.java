package scnz.api.core.utils;

import scnz.api.core.pojo.ItemEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghe on 27/02/17.
 */
public class ItemEntryList {

    private List<ItemEntry> itemEntries = new ArrayList<>();
    private Long itemId;

    public List<ItemEntry> getItemEntries() {
        return itemEntries;
    }

    public void setItemEntries(List<ItemEntry> itemEntries) {
        this.itemEntries = itemEntries;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
