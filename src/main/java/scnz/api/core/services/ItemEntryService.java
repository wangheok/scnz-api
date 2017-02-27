package scnz.api.core.services;

import scnz.api.core.pojo.Item;
import scnz.api.core.pojo.ItemEntry;

import java.util.List;

/**
 * Created by wanghe on 30/01/17.
 */
public interface ItemEntryService {
    /**
     * retrieve item entry by Id or return null if cannot be found
     *
     * @param itemId
     * @return
     */
    public ItemEntry findItemEntry(Long itemId);

    /**
     * Delete item entry by Id or return null if cannot be found
     *
     * @param itemId
     * @return
     */
    public ItemEntry deleteItemEntry(Long itemId);

    /**
     * Update item by id return null if cannot be found
     *
     * @param itemId
     * @param itemData
     * @return
     */
    public ItemEntry updateItemEntry(Long itemId, ItemEntry itemData);

}
