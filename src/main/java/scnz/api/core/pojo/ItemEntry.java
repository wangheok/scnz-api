package scnz.api.core.pojo;

/**
 * Created by wanghe on 27/02/17.
 */
public class ItemEntry {

    private Long itemEntryId;
    private String itemEntryName;
    private Item item;

    public Long getItemEntryId() {
        return itemEntryId;
    }

    public void setItemEntryId(Long itemEntryId) {
        this.itemEntryId = itemEntryId;
    }

    public String getItemEntryName() {
        return itemEntryName;
    }

    public void setItemEntryName(String itemEntryName) {
        this.itemEntryName = itemEntryName;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ItemEntry{" +
                "itemEntryId=" + itemEntryId +
                ", itemEntryName='" + itemEntryName + '\'' +
                ", item=" + item +
                '}';
    }
}
