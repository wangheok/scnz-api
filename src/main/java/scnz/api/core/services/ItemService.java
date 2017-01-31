package scnz.api.core.services;

import scnz.api.core.entities.Item;

/**
 * Created by wanghe on 30/01/17.
 */
public interface ItemService {
    /**
     * retrieve item by Id or return null if cannot be found
     *
     * @param itemId
     * @return
     */
    public Item retrieve(String itemId);

    /**
     * Delete item by Id or return null if cannot be found
     *
     * @param itemId
     * @return
     */
    public Item delete(String itemId);

    /**
     * Update item by id return null if cannot be found
     *
     * @param itemId
     * @param item
     * @return
     */
    public Item update(String itemId, Item item);

}
