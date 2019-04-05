package core;

import java.util.List;

public class Buyer extends User {

    private List<Auction> auctions;

    public Buyer(String username, String password) {
        super(username, password);
    }

    public void victory(Auction auction) {
        System.out.println("Buyer with username \'" + getUsername() +
                "\' was the victor in this auction: " +
                auction.getItemDescription() + " at this price: £" +
                auction.getWinningBid().getAmount()
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