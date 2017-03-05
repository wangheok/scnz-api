package scnz.api.core.services.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scnz.api.core.dao.ItemDao;
import scnz.api.core.pojo.Item;
import scnz.api.core.pojo.ItemEntry;
import scnz.api.core.services.ItemService;
import scnz.api.core.utils.ItemEntryList;
import scnz.api.core.utils.ItemList;

/**
 * Created by wanghe on 1/03/17.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Override
    public ItemEntry createItemEntry(Long itemId, ItemEntry itemData) {
        return null;
    }

    @Override
    public ItemList findAllItems() {
        return null;
    }

    @Override
    public ItemEntryList findAllItemEntries(Long itemId) {
        return null;
    }

    @Override
    public Item findItemById(Long itemId) {
        return null;
    }
}
