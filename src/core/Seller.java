package core;

import java.util.List;

public class Seller extends User {

    private List<Item> items;
    private List<Auction> auctions;

    public boolean isBlocked() {
        return false;
    }

    public void setBlocked(boolean isBlocked) {
    }

    @Override
    public boolean block() {
        return false;
    }

    @Override
    public boolean unBlock() {
        return false;
    }
}
