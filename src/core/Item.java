package core;

import java.util.List;

/**
 * This class contains the item being sold on the auction, including its description
 */
public class Item {
    /**
     * Private Fields
     */
    private String description;

    /**
     * This constructor will create the item and set its description
     *
     * @param description the description of the item
     */
    public Item(String description) {
        this.description = description;
    }

    /**
     * This method will return the description of the item
     *
     * @return the item description
     */
    public String getDescription() {
        return description;
    }
}