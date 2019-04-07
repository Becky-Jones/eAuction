package core;

import java.util.List;

/**
 * This class will set the user as a Buyer who can then set bids on auctions and browse all active auctions
 */
public class Buyer extends User {

    /**
     * Private Fields
     */
    private List<Auction> auctions;

    /**
     * This constructor will set the fields for the buyer
     *
     * @param username the username for the buyer
     * @param password the password for the buyer
     */
    public Buyer(String username, String password) {
        super(username, password);
    }

    /**
     * This method will tell the buyer that they have won an auciton they bid on
     *
     * @param auction the auction they have won
     */
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