package scnz.api.core.entities;

/**
 * Created by wanghe on 30/01/17.
 */
public class Item {

    private String itemId;

    private String itemName;

    public Item() {

    }

    public Item(String itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
