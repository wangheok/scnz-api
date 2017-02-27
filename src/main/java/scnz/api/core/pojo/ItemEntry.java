package scnz.api.core.pojo;

/**
 * Created by wanghe on 27/02/17.
 */
public class ItemEntry {

    private Long itemId;
    private String itemName;
    private Item item;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
