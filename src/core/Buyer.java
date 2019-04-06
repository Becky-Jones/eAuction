package core;

import java.util.List;

public class Buyer extends User {

    private List<Auction> auctions;

    public Buyer(String username, String password) {
        super(username, password);
    }

    public void victory(Auction auction) {
        String name = getUsername();
        String item = auction.getItemDescription();
        double bidValue = auction.getWinningBid().getAmount();
        System.out.println("Buyer with username \'" + name +
                "\' was the victor in the auction: " +
                item + ", at this price: £" +
                bidValue
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