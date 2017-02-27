package scnz.api.core.services;

import scnz.api.core.pojo.Item;
import scnz.api.core.pojo.ItemEntry;
import scnz.api.core.utils.ItemEntryList;
import scnz.api.core.utils.ItemList;

/**
 * Created by wanghe on 27/02/17.
 */
public interface ItemService {

    /**
     * Create item entry
     *
     * @param itemId
     * @param itemData
     * @return
     */
    public ItemEntry createItemEntry(Long itemId, ItemEntry itemData);

    /**
     * Find all items
     *
     * @return
     */
    public ItemList findAllItems();

    /**
     * Find all item entries
     *
     * @param itemId
     * @return
     */
    public ItemEntryList findAllItemEntries(Long itemId);

    /**
     * Find item by specifing ID
     *
     * @param itemId
     * @return
     */
    public Item findItemById(Long itemId);
}
