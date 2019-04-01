package core;

import java.util.List;

public class Buyer extends User {

    private List<Auction> auctions;

    public void victory(Auction auction) {
        System.out.println("Buyer with username \'" +
                this.getUsername() +
                "\' was the victor in this auction: " +
                auction.getItemDescription()
        );
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