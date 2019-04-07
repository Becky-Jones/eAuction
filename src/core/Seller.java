package core;

import java.util.List;

/**
 * This class will set the user as a Seller who can then create and verify auctions
 */
public class Seller extends User {

    /**
     * Private Fields
     */
    private List<Item> items;
    private List<Auction> auctions;

    /**
     * This constructor will set the sellers username and password
     *
     * @param username the username for the seller
     * @param password the password for the seller
     */
    public Seller(String username, String password) {
        super(username, password);
    }

    /**
     * This method will return whether the seller is blocked
     *
     * @return a boolean as to whether they are blocked
     */
    public boolean isBlocked() {
        return false;
    }

    /**
     * This method will set the seller as blocked/unblocked
     *
     * @param isBlocked a boolean to set the seller as blocked/unblocked
     */
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
