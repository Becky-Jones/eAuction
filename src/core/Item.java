package core;

import java.util.List;

public class Item {
    private String description;
    private List<Auction> auctions;


    public Item(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}