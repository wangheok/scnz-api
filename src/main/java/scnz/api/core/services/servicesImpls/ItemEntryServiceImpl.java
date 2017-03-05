package scnz.api.core.services.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scnz.api.core.dao.ItemEntryDao;
import scnz.api.core.pojo.ItemEntry;
import scnz.api.core.services.ItemEntryService;

/**
 * Created by wanghe on 1/03/17.
 */
@Service
public class ItemEntryServiceImpl implements ItemEntryService {

    @Autowired
    ItemEntryDao itemEntryDao;

    @Override
    public ItemEntry findItemEntry(Long itemEntryId) {
        return null;
    }

    @Override
    public ItemEntry deleteItemEntry(Long itemEntryId) {
        return null;
    }

    @Override
    public ItemEntry updateItemEntry(Long itemEntryId, ItemEntry itemEntryData) {
        return null;
    }
}
