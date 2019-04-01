package core;

import java.util.List;

public class Buyer extends User {

    private List<Auction> auctions;

    public void victory() {

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